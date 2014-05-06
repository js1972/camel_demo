camel_demo
==========

Apache Camel demo app (java)

Basic demo of Apache Camel in a java app. See the following blog: http://saltnlight5.blogspot.com.au/2013/08/getting-started-with-apache-camel-using.html. The current code implements the final example from the blog using beans to modularise the route.

This uses the exec-maven-plugin and can be run with the following command:

```mvn compile```

```mvn exec:java```.

The route will simply log a timer eavery second (for bean 1 and bean 2).
