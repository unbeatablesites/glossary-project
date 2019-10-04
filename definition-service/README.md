# Financial Aid Service

This microservice provides some functionality for using definition data store.


    @RequestMapping(value = "/definition/term/{term}", method = RequestMethod.GET)
    public List<Definition> getDefinitionsForTerm(@PathVariable String term) {
        return repo.findDefinitionByTerm(term);
    }

    @RequestMapping(value = "/definition", method = RequestMethod.POST)
    public Definition addDefinition(@RequestBody Definition definition) {
        definition.setId(null);
        return repo.save(definition);
    }

    @RequestMapping(value = "/definition/{id}", method = RequestMethod.GET)

* ```/definition/term/{term}``` (GET) returns a list of definitions for a given term (word or phrase).
* ```/definition``` (POST) allows for the creation of a new Definition record in the database. The post body should be of the following format:
    ```javascript
    {
        "term": "<word or phrase>",
        "definition": "<definition of the term>"
    }
    ```
    The above returns the same Definition object with a newly created numeric id.

* ```/definition/{id}``` (GET) returns the Definition object that has the given numeric ```id```.

A definition is returned in the following format:

```javascript
{
    "id": <numeric id>,
    "term": "<word or phrase>",
    "definition": "<definition of the term>"
}
```

To run this service, type the following command in the project directory:

```./gradlew clean build bootRun```

The service runs on port 8232.