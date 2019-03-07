# Below is the API documentation about the movies Service.

### **Swagger link:**
http://{host}:{port}/swagger-ui.html


### **End Point:**
**Method**|**End Point**| **Description**
---|---|---
**_GET_**|/hello|This will always 'Hello back from Server'
**_GET_** |/load/allFiles | This will Unzip and load all the files in order.
**_GET_** | /load/{fileName} | This will Unzip and load a specific file.
**_GET_** |/unzip/allFiles | This will Unzip all the files in order.
**_GET_** |/unzip/{fileName} | This will Unzip a specific file.
**_GET_** |/artist-name/name |This accepts a string and return and return list of Artists.'
**_GET_** |/artist-name/name/withTitle |This accepts a string and return and return list of Artists Details, along with all the popular Title details.
**_GET_** |/artist-name/name/{uniqueId} |This accepts the unique Artist ID and return the Artist Details.
**_GET_** |/artist-name/name/{uniqueId}/withTitle |This accepts the unique Artist ID and return the Artist Details,along with all the popular Title details.
**_GET_** |/title-name/name |This accepts a string and return and return list of Titles matching the string.
**_GET_** |/title-name/name/{uniqueId} |This accepts the unique Artist ID and return the Titles Details.
**_GET_** |/title-name/name/{uniqueId}/withName |This accepts the unique Title ID and return the Titles Details, along with all the Name Basic details.
**_GET_** |/title-name/name/{uniqueId}/withPrincipals |This accepts the unique Title ID and return the Titles Details, along with all the Title Principle details.
**_GET_** |/title-crew/{uniqueId} | Thi will return teh Crew Details based on the ID.