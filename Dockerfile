FROM neo4j:4.1.1

ENV APOC_VERSION 4.1.0.2
ENV APOC_URI https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/download/${APOC_VERSION}/apoc-${APOC_VERSION}-all.jar

RUN wget $APOC_URI && mv apoc-${APOC_VERSION}-all.jar plugins/apoc-${APOC_VERSION}-all.jar

COPY target/original-lds-neo4j-plugin-1.0.0-SNAPSHOT.jar plugins/lds-neo4j-plugin-1.0.0-SNAPSHOT.jar
