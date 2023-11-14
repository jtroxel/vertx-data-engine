
/*
* (c) Copyright IBM Corporation 2021
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.asyncapi;
  
import java.util.logging.*;
import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSRuntimeException;
import javax.jms.JMSException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonView;

import com.asyncapi.ConnectionHelper;
import com.asyncapi.LoggingHelper;
import com.asyncapi.Connection;
import com.asyncapi.PubSubBase;

import com.asyncapi.models.ModelContract;
import com.asyncapi.models.Song;

public class SongReleasedSubscriber  extends PubSubBase{

  private JMSConsumer consumer = null;
    
  public SongReleasedSubscriber() {
    
      super();
      String id = null;
      id = "Basic sub";
      logger.info("Sub application is starting");

      this.createConnection("song/released", id);
  
  }
  public void receive(int requestTimeout) {
    boolean continueProcessing = true;

    consumer = context.createConsumer(destination);
    logger.info("consumer created");

    while (continueProcessing) {
        try {
            // Receive message from MQ
            Message receivedMessage = consumer.receive(requestTimeout);

            // Check message has been received
            if (receivedMessage == null) {
                logger.info("No message received from this endpoint");
                continue;
            }

            // Find message type
            if (receivedMessage instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) receivedMessage;
                try {
                    logger.info("Received message: " + textMessage.getText());
                    Song receivedObject = new ObjectMapper().readValue(textMessage.getText(), Song.class);
                    logger.info("Received message type: " + receivedObject.getClass().getName());

                    /*
                     * Implement your business logic to handle
                     * received messages here.
                     */
                    
                } catch (JMSException jmsex) {
                    recordFailure(jmsex);
                } catch (JsonProcessingException jsonproex) {
                    recordFailure(jsonproex);
                }
            } else if (receivedMessage instanceof Message) {
                logger.info("Message received was not of type TextMessage.");
            } else {
                logger.info("Received object not of JMS Message type.");
            }
        } catch (JMSRuntimeException jmsex) {
            jmsex.printStackTrace();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
      }
  }
  
}
