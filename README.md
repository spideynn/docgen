# docgen
Generate documentation from a text document.

# Usage
**The text document has to have the first line being the title, second line is the copyright, and rest is text.**
`` java -jar docgen.jar --in <filename.txt> --out <filename.html> ``

# Using your own template (WIP support)

**Your template MUST have an area for a title, text and copyright.**

ex. ``{{copyright}}``, ``{{title}}``, ``{{text}}``

Specify your template via ``--template <filename.html>``