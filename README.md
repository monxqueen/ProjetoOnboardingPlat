# Projeto Onboarding de Plataforma - StoreApp
Esse projeto consiste em um aplicativo de listagem de lojas que faz requisições em uma API que roda localmente. Ele é composto por duas features: 
- Favorite: apresenta a lista de lojas favoritas,
- Nearby: apresenta a lista de lojas favoritadas pelo usuário, com a adição da distância entre o usuário e cada loja. <br><br>

# Descrição do projeto
É multi-modular e segue práticas de arquitetura limpa e princípios do SOLID.
Entre as bibliotecas utilizadas, constam:
- Retrofit (para fazer chamadas REST na API)
- RxJava/RxKotlin/RxAndroid (para programação reativa/concorrente)
- Kotlin Serialization (para serialização e desserialização)
- Koin (para injeção de dependência)
- junit (para testes unitários)
- Espresso (para testes de interface)
- mockito (para mocks necessários em testes)
- mockWebServer (para mocks de API necessários em testes instrumentados), etc.<br><br>

**Referências**<br>
<a href="https://github.com/andresfabreu/android-code-training">Enunciado/proposta do projeto</a><br>
<a href="https://github.com/andresfabreu/android-training-api">API</a>
