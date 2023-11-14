# Record Label Service

This service is in charge of processing music

## Install Maven
For instructions on installing maven for your operating system, please see the [Apache Maven site](https://maven.apache.org/install.html).

## Running the Publisher/Subscriber template
These commands will allow you to run the template publisher/subscriber model using IBM MQ.

1. Install dependencies
    ```sh
    mvn compile
    ```

    This command uses maven to install all the required dependences needed for this template to run.


2. Create .jar package
    ```sh
    mvn package
    ```
    Using maven generate the .jar file to run.

3. Run Publisher
    ```sh
    java -cp target/asyncapi-java-generator-0.1.0.jar: com.asyncapi.DemoProducer
    ```

    This command runs the publisher function of this template from the generated .jar file.


4. In a separate terminal, Run Subscriber
    ```sh
    java -cp target/asyncapi-java-generator-0.1.0.jar: com.asyncapi.DemoSubscriber
    ```

    This command runs the subscriber function of this template from the generated .jar file.


The messages should now be sent from the running publisher to the running subscriber, using MQ topics.
    