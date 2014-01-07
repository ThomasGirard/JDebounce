Debouncing is a technique similar to Throttling. 
It's best described here http://unscriptable.com/2009/03/20/debouncing-javascript-methods/

Popular javascript libraries (jQuery, _.js) provide debouncing helpers. This project aims to provide a similar reusable library for Java. 

There are two parts to this project:

1. The Debouncing framework that provides helper classes for debouncing methods. This framework doesn't have any dependencies and can be quickly reused in any project.
2. A Debounce annotation and matching AOP aspect to debounce methods declaratively. 
	The annotation has been successfully tested with Spring-AOP. 
	
Both use cases are demonstrated in tests. 
