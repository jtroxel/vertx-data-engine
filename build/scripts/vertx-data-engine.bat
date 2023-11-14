@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  vertx-data-engine startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and VERTX_DATA_ENGINE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\vertx-data-engine-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\vertx-config-4.4.6.jar;%APP_HOME%\lib\vertx-hazelcast-4.4.6.jar;%APP_HOME%\lib\vertx-json-schema-4.4.6.jar;%APP_HOME%\lib\vertx-consul-client-4.4.6.jar;%APP_HOME%\lib\vertx-kafka-client-4.4.6.jar;%APP_HOME%\lib\vertx-opentracing-4.4.6.jar;%APP_HOME%\lib\vertx-lang-kotlin-4.4.6.jar;%APP_HOME%\lib\vertx-reactive-streams-4.4.6.jar;%APP_HOME%\lib\camel-core-4.0.2.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.7.21.jar;%APP_HOME%\lib\hazelcast-jet-4.5.jar;%APP_HOME%\lib\vertx-junit5-4.4.6.jar;%APP_HOME%\lib\vertx-web-client-4.4.6.jar;%APP_HOME%\lib\vertx-uri-template-4.4.6.jar;%APP_HOME%\lib\vertx-web-common-4.4.6.jar;%APP_HOME%\lib\vertx-auth-common-4.4.6.jar;%APP_HOME%\lib\vertx-core-4.4.6.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.100.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.100.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.100.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.100.Final.jar;%APP_HOME%\lib\netty-handler-4.1.100.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.100.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.100.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.100.Final.jar;%APP_HOME%\lib\netty-codec-4.1.100.Final.jar;%APP_HOME%\lib\netty-transport-4.1.100.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.100.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.100.Final.jar;%APP_HOME%\lib\netty-common-4.1.100.Final.jar;%APP_HOME%\lib\camel-yaml-io-4.0.2.jar;%APP_HOME%\lib\jackson-core-2.15.3.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.15.3.jar;%APP_HOME%\lib\jackson-databind-2.15.3.jar;%APP_HOME%\lib\jackson-annotations-2.15.3.jar;%APP_HOME%\lib\snakeyaml-2.1.jar;%APP_HOME%\lib\camel-core-languages-4.0.2.jar;%APP_HOME%\lib\camel-file-4.0.2.jar;%APP_HOME%\lib\camel-cluster-4.0.2.jar;%APP_HOME%\lib\camel-health-4.0.2.jar;%APP_HOME%\lib\kafka-clients-3.5.0.jar;%APP_HOME%\lib\camel-xml-io-4.0.2.jar;%APP_HOME%\lib\camel-xml-jaxb-4.0.2.jar;%APP_HOME%\lib\camel-core-engine-4.0.2.jar;%APP_HOME%\lib\camel-core-reifier-4.0.2.jar;%APP_HOME%\lib\camel-bean-4.0.2.jar;%APP_HOME%\lib\camel-browse-4.0.2.jar;%APP_HOME%\lib\camel-controlbus-4.0.2.jar;%APP_HOME%\lib\camel-dataformat-4.0.2.jar;%APP_HOME%\lib\camel-direct-4.0.2.jar;%APP_HOME%\lib\camel-language-4.0.2.jar;%APP_HOME%\lib\camel-log-4.0.2.jar;%APP_HOME%\lib\camel-dataset-4.0.2.jar;%APP_HOME%\lib\camel-mock-4.0.2.jar;%APP_HOME%\lib\camel-ref-4.0.2.jar;%APP_HOME%\lib\camel-rest-4.0.2.jar;%APP_HOME%\lib\camel-saga-4.0.2.jar;%APP_HOME%\lib\camel-scheduler-4.0.2.jar;%APP_HOME%\lib\camel-stub-4.0.2.jar;%APP_HOME%\lib\camel-seda-4.0.2.jar;%APP_HOME%\lib\camel-timer-4.0.2.jar;%APP_HOME%\lib\camel-validator-4.0.2.jar;%APP_HOME%\lib\camel-xpath-4.0.2.jar;%APP_HOME%\lib\camel-xslt-4.0.2.jar;%APP_HOME%\lib\camel-xml-jaxp-4.0.2.jar;%APP_HOME%\lib\camel-core-model-4.0.2.jar;%APP_HOME%\lib\camel-base-engine-4.0.2.jar;%APP_HOME%\lib\camel-base-4.0.2.jar;%APP_HOME%\lib\camel-core-processor-4.0.2.jar;%APP_HOME%\lib\camel-support-4.0.2.jar;%APP_HOME%\lib\camel-management-api-4.0.2.jar;%APP_HOME%\lib\camel-core-catalog-4.0.2.jar;%APP_HOME%\lib\camel-api-4.0.2.jar;%APP_HOME%\lib\camel-util-4.0.2.jar;%APP_HOME%\lib\jaeger-client-1.0.0.jar;%APP_HOME%\lib\jaeger-thrift-1.0.0.jar;%APP_HOME%\lib\jaeger-tracerresolver-1.0.0.jar;%APP_HOME%\lib\jaeger-core-1.0.0.jar;%APP_HOME%\lib\libthrift-0.12.0.jar;%APP_HOME%\lib\slf4j-api-2.0.7.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.7.21.jar;%APP_HOME%\lib\kotlin-stdlib-1.7.21.jar;%APP_HOME%\lib\hazelcast-4.2.8.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\camel-xml-jaxp-util-4.0.2.jar;%APP_HOME%\lib\camel-tooling-model-4.0.2.jar;%APP_HOME%\lib\camel-xml-io-util-4.0.2.jar;%APP_HOME%\lib\jaxb-impl-4.0.3.jar;%APP_HOME%\lib\jaxb-core-4.0.3.jar;%APP_HOME%\lib\jakarta.xml.bind-api-4.0.0.jar;%APP_HOME%\lib\camel-util-json-4.0.2.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.7.21.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\junit-jupiter-engine-5.8.2.jar;%APP_HOME%\lib\junit-platform-engine-1.8.2.jar;%APP_HOME%\lib\junit-platform-commons-1.8.2.jar;%APP_HOME%\lib\junit-jupiter-params-5.8.2.jar;%APP_HOME%\lib\junit-jupiter-api-5.8.2.jar;%APP_HOME%\lib\zstd-jni-1.5.5-1.jar;%APP_HOME%\lib\lz4-java-1.8.0.jar;%APP_HOME%\lib\snappy-java-1.1.10.0.jar;%APP_HOME%\lib\angus-activation-2.0.1.jar;%APP_HOME%\lib\jakarta.activation-api-2.1.2.jar;%APP_HOME%\lib\okhttp-3.9.0.jar;%APP_HOME%\lib\opentracing-util-0.33.0.jar;%APP_HOME%\lib\opentracing-tracerresolver-0.1.8.jar;%APP_HOME%\lib\opentracing-noop-0.33.0.jar;%APP_HOME%\lib\opentracing-api-0.33.0.jar;%APP_HOME%\lib\gson-2.8.2.jar;%APP_HOME%\lib\opentest4j-1.2.0.jar;%APP_HOME%\lib\okio-1.13.0.jar


@rem Execute vertx-data-engine
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %VERTX_DATA_ENGINE_OPTS%  -classpath "%CLASSPATH%" io.vertx.core.Launcher %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable VERTX_DATA_ENGINE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%VERTX_DATA_ENGINE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
