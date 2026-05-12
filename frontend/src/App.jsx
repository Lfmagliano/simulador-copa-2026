import { useState, useEffect } from "react"
import 'flag-icons/css/flag-icons.min.css'

const codigos = {
  "México": "mx",
  "África do Sul": "za",
  "Coreia do Sul": "kr",
  "República Tcheca": "cz",
  "Canadá": "ca",
  "Bósnia": "ba",
  "Qatar": "qa",
  "Suíça": "ch",
  "Brasil": "br",
  "Marrocos": "ma",
  "Haiti": "ht",
  "Escócia": "gb-sct",
  "Estados Unidos": "us",
  "Paraguai": "py",
  "Austrália": "au",
  "Turquia": "tr",
  "Alemanha": "de",
  "Curaçao": "cw",
  "Costa do Marfim": "ci",
  "Equador": "ec",
  "Holanda": "nl",
  "Japão": "jp",
  "Suécia": "se",
  "Tunísia": "tn",
  "Bélgica": "be",
  "Egito": "eg",
  "Irã": "ir",
  "Nova Zelândia": "nz",
  "Espanha": "es",
  "Cabo Verde": "cv",
  "Arábia Saudita": "sa",
  "Uruguai": "uy",
  "França": "fr",
  "Senegal": "sn",
  "Iraque": "iq",
  "Noruega": "no",
  "Argentina": "ar",
  "Argélia": "dz",
  "Áustria": "at",
  "Jordânia": "jo",
  "Portugal": "pt",
  "RD Congo": "cd",
  "Uzbequistão": "uz",
  "Colômbia": "co",
  "Inglaterra": "gb-eng",
  "Croácia": "hr",
  "Gana": "gh",
  "Panamá": "pa",
}

function App() {
  const [grupos, setGrupos] = useState([])
  const [selecoes, setSelecoes] = useState([])
  const [jogos, setJogos] = useState([])
  const [classificacoes, setClassificacoes] = useState({})
  const [aba, setAba] = useState("rodadas")
  const [rodadaAtiva, setRodadaAtiva] = useState(1)
  const [grupoAtivo, setGrupoAtivo] = useState(null)

  const carregarDados = async () => {
    const [g, s, j] = await Promise.all([
      fetch("/api/grupos").then(r => r.json()),
      fetch("/api/selecoes").then(r => r.json()),
      fetch("/api/jogos").then(r => r.json()),
    ])
    setGrupos(g)
    setSelecoes(s)
    setJogos(j)
    const classifs = {}
    for (const grupo of g) {
      const c = await fetch(`/api/grupos/${grupo.id}/classificacao`).then(r => r.json())
      classifs[grupo.nome] = c
    }
    setClassificacoes(classifs)
  }

  useEffect(() => { carregarDados() }, [])

  const jogosDaRodada = (rodada) =>
    jogos.filter(j => j.fase === "GRUPOS" && j.rodada === rodada)

  const jogosDo = (grupoNome, rodada) =>
    jogos.filter(j =>
      j.fase === "GRUPOS" &&
      j.selecaoCasa.grupo.nome === grupoNome &&
      j.rodada === rodada
    )

  const jogosDaFase = (fase) =>
    jogos.filter(j => j.fase === fase)

  const selecoesDo = (grupoNome) =>
    selecoes.filter(s => s.grupo.nome === grupoNome)

  const rodadaCompleta = (rodada) =>
    jogosDaRodada(rodada).length > 0 && jogosDaRodada(rodada).every(j => j.encerrado)

  const rodadaDoGrupoCompleta = (grupoNome, rodada) =>
    jogosDo(grupoNome, rodada).every(j => j.encerrado)

  const faseCompleta = (fase) =>
    jogosDaFase(fase).length > 0 && jogosDaFase(fase).every(j => j.encerrado)

  const getVencedor = (jogo) => {
    if (!jogo.encerrado) return null
    if (jogo.golsCasa > jogo.golsVisitante) return jogo.selecaoCasa.nome
    if (jogo.golsVisitante > jogo.golsCasa) return jogo.selecaoVisitante.nome
    return jogo.selecaoCasa.forca >= jogo.selecaoVisitante.forca
      ? jogo.selecaoCasa.nome : jogo.selecaoVisitante.nome
  }

  const simular = async (jogoId) => {
    await fetch(`/api/jogos/simular/${jogoId}`, { method: "POST" })
    carregarDados()
  }

  const simularRodadaCompleta = async (rodada) => {
    for (const jogo of jogosDaRodada(rodada).filter(j => !j.encerrado)) {
      await fetch(`/api/jogos/simular/${jogo.id}`, { method: "POST" })
    }
    carregarDados()
  }

  const simularRodadaDoGrupo = async (grupoNome, rodada) => {
    for (const jogo of jogosDo(grupoNome, rodada).filter(j => !j.encerrado)) {
      await fetch(`/api/jogos/simular/${jogo.id}`, { method: "POST" })
    }
    carregarDados()
  }

  const simularFase = async (fase) => {
    for (const jogo of jogosDaFase(fase).filter(j => !j.encerrado)) {
      await fetch(`/api/jogos/simular/${jogo.id}`, { method: "POST" })
    }
    carregarDados()
  }

  const gruposOrdenados = [...grupos].sort((a, b) => a.nome.localeCompare(b.nome))

  const Flag = ({ nome }) => (
    <span
      className={`fi fi-${codigos[nome] || "un"}`}
      style={{ width: 20, height: 15, display: "inline-block", flexShrink: 0 }}
    />
  )

  const CardJogo = ({ jogo }) => {
    const vencedor = getVencedor(jogo)
    const casaVenceu = vencedor === jogo.selecaoCasa.nome
    const visitanteVenceu = vencedor === jogo.selecaoVisitante.nome
  
    return (
      <div className="bg-gray-700/50 rounded-lg p-2 flex items-center gap-2 text-sm">
        <div className={`flex items-center gap-1 justify-end flex-1 min-w-0 ${casaVenceu ? "text-green-400 font-bold" : ""}`}>
          {jogo.temPenaltis && casaVenceu &&
            <span className="text-xs text-green-400 whitespace-nowrap flex-shrink-0">(pen)</span>}
          <span className="truncate text-right">{jogo.selecaoCasa.nome}</span>
          <Flag nome={jogo.selecaoCasa.nome} />
        </div>
  
        <span className="text-yellow-400 font-bold whitespace-nowrap flex-shrink-0">
          {jogo.encerrado ? `${jogo.golsCasa} x ${jogo.golsVisitante}` : "vs"}
        </span>
  
        <div className={`flex items-center gap-1 flex-1 min-w-0 ${visitanteVenceu ? "text-green-400 font-bold" : ""}`}>
          <Flag nome={jogo.selecaoVisitante.nome} />
          <span className="truncate">{jogo.selecaoVisitante.nome}</span>
          {jogo.temPenaltis && visitanteVenceu &&
            <span className="text-xs text-green-400 whitespace-nowrap flex-shrink-0">(pen)</span>}
        </div>
  
        <div className="flex-shrink-0">
          {!jogo.encerrado ? (
            <button
              onClick={() => simular(jogo.id)}
              className="bg-green-600 hover:bg-green-500 text-white px-2 py-1 rounded text-xs font-bold transition-all"
            >▶</button>
          ) : (
            <span className="text-green-400 text-xs">✓</span>
          )}
        </div>
      </div>
    )
  }

  const TabelaClassificacao = ({ grupoNome }) => {
    const classif = classificacoes[grupoNome] || []
    if (classif.length === 0) return null
    return (
      <table className="w-full text-xs mt-2">
        <thead>
          <tr className="text-gray-400 border-b border-gray-600">
            <th className="text-left pb-1">#</th>
            <th className="text-left pb-1">Seleção</th>
            <th className="text-center pb-1">Pts</th>
            <th className="text-center pb-1">V</th>
            <th className="text-center pb-1">E</th>
            <th className="text-center pb-1">D</th>
            <th className="text-center pb-1">GP</th>
            <th className="text-center pb-1">GC</th>
            <th className="text-center pb-1">SG</th>
          </tr>
        </thead>
        <tbody>
          {classif.map((c, idx) => (
            <tr key={c.id} className={`border-b border-gray-700/50 ${
              idx < 2 ? "text-green-400" : idx === 2 ? "text-yellow-400" : "text-gray-400"
            }`}>
              <td className="py-1 font-bold">{idx + 1}º</td>
              <td className="py-1 font-medium">
                <div className="flex items-center gap-1">
                  <Flag nome={c.selecao.nome} />
                  {c.selecao.nome}
                </div>
              </td>
              <td className="text-center font-bold">{c.pontos}</td>
              <td className="text-center">{c.vitorias}</td>
              <td className="text-center">{c.empates}</td>
              <td className="text-center">{c.derrotas}</td>
              <td className="text-center">{c.golsPro}</td>
              <td className="text-center">{c.golsContra}</td>
              <td className="text-center">{c.saldoGols > 0 ? `+${c.saldoGols}` : c.saldoGols}</td>
            </tr>
          ))}
        </tbody>
      </table>
    )
  }

  const fases = [
    { key: "DEZESSEIS_AVOS", label: "Rodada de 32" },
    { key: "OITAVAS", label: "Oitavas de Final" },
    { key: "QUARTAS", label: "Quartas de Final" },
    { key: "SEMIFINAL", label: "Semifinal" },
    { key: "TERCEIRO_LUGAR", label: "3º Lugar" },
    { key: "FINAL", label: "🏆 Final" },
  ]

  return (
    <div style={{ height: "100vh", overflow: "hidden" }} className="bg-gray-950 text-white p-6 flex flex-col">

      {/* Header */}
      <div className="flex items-center justify-between mb-4 border-b border-gray-700 pb-4 flex-shrink-0">
        <h1 className="text-2xl font-bold text-yellow-400">🏆 Copa do Mundo 2026</h1>
        <div className="flex gap-3">
          {[
            { id: "rodadas", label: "📅 Rodadas" },
            { id: "grupos", label: "🌍 Grupos" },
            { id: "classificacao", label: "📊 Classificação" },
            { id: "matamata", label: "🏆 Mata-Mata" },
          ].map(a => (
            <button
              key={a.id}
              onClick={() => setAba(a.id)}
              className={`px-6 py-2 rounded-lg font-bold transition-all ${
                aba === a.id
                  ? "bg-yellow-400 text-gray-900"
                  : "bg-gray-800 text-yellow-400 border border-gray-700 hover:border-yellow-400"
              }`}
            >
              {a.label}
            </button>
          ))}
        </div>
        <button
          onClick={async () => {
            if (confirm("Tem certeza que deseja resetar toda a simulação?")) {
              await fetch("/api/admin/reset", { method: "POST" })
              alert("Simulação resetada com sucesso!")
              window.location.reload()
            }
          }}
          className="bg-red-600 hover:bg-red-500 text-white px-3 py-2 rounded-lg text-sm font-bold transition-all"
        >
          🔄 Resetar
        </button>
      </div>

      {/* Conteúdo */}
      <div style={{ flex: 1, overflowY: "auto", minHeight: 0 }}>

        {/* ABA RODADAS */}
        {aba === "rodadas" && (
          <div>
            <div className="flex gap-3 mb-4">
              {[1, 2, 3].map(r => (
                <button
                  key={r}
                  onClick={() => setRodadaAtiva(r)}
                  className={`flex-1 py-2 rounded-xl font-bold border transition-all ${
                    rodadaAtiva === r
                      ? "bg-yellow-400 text-gray-900 border-yellow-400"
                      : "bg-gray-800 text-yellow-400 border-gray-700 hover:border-yellow-400"
                  }`}
                >
                  {rodadaCompleta(r) ? "✅" : "⏳"} {r}ª Rodada
                </button>
              ))}
            </div>
            {!rodadaCompleta(rodadaAtiva) && (
              <button
                onClick={() => simularRodadaCompleta(rodadaAtiva)}
                className="w-full py-2 mb-4 rounded-xl font-bold bg-green-600 hover:bg-green-500 text-white transition-all"
              >
                ⚡ Simular Todos os Jogos da {rodadaAtiva}ª Rodada
              </button>
            )}
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-3 pb-4">
              {gruposOrdenados.map(grupo => {
                const jogosDoGrupo = jogosDo(grupo.nome, rodadaAtiva)
                const grupoCompleto = rodadaDoGrupoCompleta(grupo.nome, rodadaAtiva)
                return (
                  <div key={grupo.id} className="bg-gray-800 rounded-xl p-3 border border-gray-700">
                    <div className="flex justify-between items-center mb-2">
                      <h3 className="text-yellow-400 font-bold">Grupo {grupo.nome}</h3>
                      {!grupoCompleto ? (
                        <button
                          onClick={() => simularRodadaDoGrupo(grupo.nome, rodadaAtiva)}
                          className="bg-yellow-400 text-gray-900 px-2 py-1 rounded text-xs font-bold hover:bg-yellow-300 transition-all"
                        >Simular</button>
                      ) : (
                        <span className="text-green-400 text-xs">✅ Completo</span>
                      )}
                    </div>
                    <div className="space-y-1">
                      {jogosDoGrupo.map(jogo => <CardJogo key={jogo.id} jogo={jogo} />)}
                    </div>
                  </div>
                )
              })}
            </div>
          </div>
        )}

        {/* ABA GRUPOS */}
        {aba === "grupos" && (
          <div>
            <div className="grid grid-cols-4 lg:grid-cols-6 gap-2 mb-4">
              {gruposOrdenados.map(grupo => (
                <button
                  key={grupo.id}
                  onClick={() => setGrupoAtivo(grupoAtivo === grupo.nome ? null : grupo.nome)}
                  className={`p-2 rounded-xl font-bold border transition-all ${
                    grupoAtivo === grupo.nome
                      ? "bg-yellow-400 text-gray-900 border-yellow-400"
                      : "bg-gray-800 text-yellow-400 border-gray-700 hover:border-yellow-400"
                  }`}
                >
                  {grupo.nome}
                </button>
              ))}
            </div>
            {grupoAtivo && (
              <div className="grid grid-cols-1 lg:grid-cols-2 gap-4 pb-4">
                <div className="bg-gray-800 rounded-xl p-4 border border-gray-700">
                  <h3 className="text-yellow-400 font-bold text-lg mb-3">🌍 Grupo {grupoAtivo}</h3>
                  <table className="w-full text-sm">
                    <thead>
                      <tr className="text-gray-400 border-b border-gray-700">
                        <th className="text-left pb-2">Seleção</th>
                        <th className="text-center pb-2">Força</th>
                        <th className="text-center pb-2">Continente</th>
                      </tr>
                    </thead>
                    <tbody>
                      {selecoesDo(grupoAtivo).map(s => (
                        <tr key={s.id} className="border-b border-gray-700/50">
                          <td className="py-2 font-medium">
                            <div className="flex items-center gap-1">
                              <Flag nome={s.nome} />
                              {s.nome}
                            </div>
                          </td>
                          <td className="text-center">
                            <span className="bg-yellow-400 text-gray-900 px-2 py-0.5 rounded font-bold text-xs">{s.forca}</span>
                          </td>
                          <td className="text-center text-gray-400 text-xs">{s.continente}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
                <div className="space-y-2">
                  {[1, 2, 3].map(r => (
                    <div key={r} className="bg-gray-800 rounded-xl p-3 border border-gray-700">
                      <div className="flex justify-between items-center mb-2">
                        <h4 className="text-yellow-400 font-bold text-sm">{r}ª Rodada</h4>
                        {!rodadaDoGrupoCompleta(grupoAtivo, r) && (
                          <button
                            onClick={() => simularRodadaDoGrupo(grupoAtivo, r)}
                            className="bg-yellow-400 text-gray-900 px-2 py-1 rounded text-xs font-bold hover:bg-yellow-300 transition-all"
                          >Simular</button>
                        )}
                      </div>
                      <div className="space-y-1">
                        {jogosDo(grupoAtivo, r).map(jogo => <CardJogo key={jogo.id} jogo={jogo} />)}
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>
        )}

        {/* ABA CLASSIFICAÇÃO */}
        {aba === "classificacao" && (
          <div className="grid grid-cols-2 lg:grid-cols-4 gap-3 pb-4">
            {gruposOrdenados.map(grupo => (
              <div key={grupo.id} className="bg-gray-800 rounded-xl p-3 border border-gray-700">
                <h3 className="text-yellow-400 font-bold mb-1 text-sm">Grupo {grupo.nome}</h3>
                <div className="text-xs text-gray-500 mb-1 flex gap-2">
                  <span className="w-2 h-2 rounded-full bg-green-400 inline-block mt-0.5"></span>
                  <span>Classificado</span>
                  <span className="w-2 h-2 rounded-full bg-yellow-400 inline-block mt-0.5 ml-1"></span>
                  <span>Melhor 3º</span>
                </div>
                <TabelaClassificacao grupoNome={grupo.nome} />
              </div>
            ))}
          </div>
        )}

        {/* ABA MATA-MATA */}
        {aba === "matamata" && (
          <div className="space-y-4 pb-4">
            {jogosDaFase("DEZESSEIS_AVOS").length === 0 && (
              <div className="text-center text-gray-400 py-20">
                <p className="text-2xl mb-2">⏳</p>
                <p>O mata-mata será gerado automaticamente após todos os jogos da fase de grupos serem simulados.</p>
              </div>
            )}
            {fases.map(fase => {
              const jogosF = jogosDaFase(fase.key)
              if (jogosF.length === 0) return null
              const completa = faseCompleta(fase.key)
              return (
                <div key={fase.key} className="bg-gray-800 rounded-xl p-4 border border-gray-700">
                  <div className="flex justify-between items-center mb-3">
                    <h3 className="text-yellow-400 font-bold">{fase.label}</h3>
                    {!completa ? (
                      <button
                        onClick={() => simularFase(fase.key)}
                        className="bg-green-600 hover:bg-green-500 text-white px-3 py-1 rounded-lg text-sm font-bold transition-all"
                      >⚡ Simular Fase</button>
                    ) : (
                      <span className="text-green-400 text-sm">✅ Completo</span>
                    )}
                  </div>
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-2">
                    {jogosF.map(jogo => <CardJogo key={jogo.id} jogo={jogo} />)}
                  </div>
                </div>
              )
            })}
          </div>
        )}

      </div>
    </div>
  )
}

export default App