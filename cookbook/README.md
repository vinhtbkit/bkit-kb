### About cookbook
In this folder, we try to create the valuable recipes, so you can refer this quickly and adapt into your project.

### Recipe template
Every recipe in this cookbook should include the following required details
- README.md: A `README` file should exist in the root folder of the recipe, including the following sections
  - Overview: telling the readers what the recipe for
  - Setup: how to setup the project. Normally it's included in the pom.xml, but any additional environments or arguments needed, specify it here
  - Run: how to run this project with specified use-case
- pom.xml: including necessary dependendcies, and their versions
- The project should be named so the viewer can quickly have an understanding of what it does, and optionally the versions
  - E.g: `hibernate-json-type-mappings-62` -> Hibernate how to map JSON type for version 6.2
 
### Issues
You can find the required issues, with label `cookbook-recipe` in the `Issues` tab. Try to create a branch and submit a pull request
