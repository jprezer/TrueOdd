🦁 Aposta AI - AI Betting Assistant

"O Vidente das Apostas" - Uma ferramenta Fullstack para encontrar apostas de valor (+EV) usando estatísticas reais e Distribuição de Poisson.

🧠 O Projeto

O Aposta AI é uma aplicação web que ajuda apostadores a tomarem decisões mais inteligentes. Ele não chuta resultados; ele calcula a Probabilidade Real de um time vencer baseando-se no desempenho recente (ataque vs defesa) e compara com a Odd oferecida pela casa de apostas (Superbet, Bet365, etc).

Se a Odd da casa for maior que a Odd Justa calculada pela IA, temos uma Value Bet (Aposta de Valor).

🚀 Funcionalidades

Sincronização de Dados: Baixa dados atualizados de ligas reais (Brasileirão, Premier League, La Liga, etc) via API externa.

Cálculo de Poisson: Algoritmo matemático que simula o placar provável baseando-se na força de ataque e defesa dos times.

Cálculo de EV (Valor Esperado): Diz exatamente se a aposta é lucrativa a longo prazo.

Interface Moderna: Frontend responsivo feito em React e Tailwind, focado em uso mobile.

🛠️ Arquitetura e Tecnologias

O projeto segue uma arquitetura moderna de microsserviços na nuvem:

Backend (API): Java Spring Boot

Hospedado no Render.

Usa OpenFeign para consumir a API football-data.org.

Banco de dados H2 (Local) e PostgreSQL/Neon (Produção).

Frontend (Web): React + Vite

Hospedado no Firebase Hosting.

Estilização com Tailwind CSS e ícones Lucide.

⚙️ Como Rodar Localmente

Pré-requisitos

Java 17+

Maven

Node.js 18+

Token da API football-data.org (Grátis)

1. Backend (API)

# Clone o repositório
git clone [https://github.com/seu-usuario/TrueOdd.git](https://github.com/seu-usuario/TrueOdd.git)

# Entre na pasta da API
cd TrueOdd/api_trueodd

# Configure seu Token no application.properties ou via variável de ambiente
# export FOOTBALL_TOKEN=seu_token_aqui

# Rode o projeto
mvn spring-boot:run


A API subirá em http://localhost:8080

2. Frontend (Web)

# Entre na pasta do frontend
cd TrueOdd/trueodd-web

# Instale as dependências
npm install

# Rode o servidor de desenvolvimento
npm run dev


O site abrirá em http://localhost:5173

☁️ Deploy (Produção)

Este projeto está configurado para rodar 100% na nuvem gratuitamente:

Banco de Dados: Neon.tech (PostgreSQL Serverless).

API Java: Render (Deploy via Dockerfile).

Variáveis de Ambiente necessárias: DATABASE_URL, DB_USERNAME, DB_PASSWORD, FOOTBALL_TOKEN.

Frontend: Firebase Hosting.

Comandos: npm run build -> firebase deploy.


🤝 Contribuição

Este é um projeto pessoal de estudo. Sinta-se livre para fazer um fork e testar novas ligas ou algoritmos de previsão!
