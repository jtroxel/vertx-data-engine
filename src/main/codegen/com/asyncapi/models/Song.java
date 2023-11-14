
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
package com.asyncapi.models;
  
import com.asyncapi.models.ModelContract;
import java.util.UUID;
public class Song  extends ModelContract{
  public String title;
  public String artist;
  public String album;
  public String genre;
  public int length;
  public Song(String title,String artist,String album,String genre,int length) {
    
    this.title = title;
    
    this.artist = artist;
    
    this.album = album;
    
    this.genre = genre;
    
    this.length = length;
    
  }
  public Song() {
    super();
  }
}
