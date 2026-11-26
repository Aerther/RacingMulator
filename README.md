# Racingmulator

**Integrantes:** Arthur e Mathias  
**Turma:** 4° TI

## Sobre o projeto

Racingmulator é um simulador de automobilismo executado pelo terminal, no qual o jogador pode construir sua carreira como piloto e competir em diferentes categorias.

## Como executar

### Executar o jogo

1. Clone o repositório:
   ```cmd
   git clone https://github.com/Aerther/RacingMulator.git
   ```
2. Entre na pasta do projeto:
   ```cmd
   cd racingmulator
   ```
3. Execute o jogo:
   ```cmd
   java -jar Main.jar
   ```

É necessário ter o Java instalado para executar o programa.

### Compilar após alterações

Caso queira modificar o código-fonte, será necessário recompilar o projeto antes de executá-lo.

Na pasta raiz do projeto, execute:

```cmd
kotlinc Main.kt src\constants\*.kt src\utils\*.kt src\visual\*.kt src\models\*.kt src\enums\*.kt src\service\*.kt -include-runtime -d Main.jar
```

Depois, execute novamente:

```cmd
java -jar Main.jar
```

É necessário ter o compilador Kotlin instalado e configurado no PATH para realizar a compilação.