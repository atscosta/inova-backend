# inova-backend

## sgt-soap-client

Como resolver o problema das dependências exksoap:exksoap e exksoap:ksoap2-android-assembly

1. Adicione o plugin (código ao final) ao projeto `sgt-soap-client`. Ele vai copiar as dependências para dentro do pacote final. 
2. Dê um build no projeto sgt-soap-client `mvn clean compile`. Apesar de dar erro, será criada a estrutura dos diretórios no repositório local.
3. Navegue até a pasta referente a `groupId/artifactId/version`. Adicione os `.jars` e crie um arquivo `pom.xml` básico, apenas informando os dados de `groupId:artifactId:version`.
4. Pronto. Ao tentar o build novamente em `sgt-soap-client`, o maven conseguirá resolver a dependência e adicionar na pasta `lib` do pacote gerado. 

> Adicione o plugin (código ao final) ao projeto `sgt-soap-client`.

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <executions>
        <execution>
            <phase>install</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <outputDirectory>${project.build.directory}/lib</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
```
