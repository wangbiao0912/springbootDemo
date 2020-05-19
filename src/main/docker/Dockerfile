#FROM lwieske/java-8:jre-8u121-slim
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD tensorflow-learn-demo.jar app.jar
ENV LANG C.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV LANG zh_CN.UTF-8
ENV LANGUAGE zh_CN:zh
ENV LC_ALL zh_CN.UTF-8
ADD simsun.ttc /usr/lib/jvm/java-8-oracle/lib/fonts/simsun.ttc
ADD simsunb.ttf /usr/lib/jvm/java-8-oracle/lib/fonts/simsunb.ttf
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx2000m","-Xms256m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 9999。、
