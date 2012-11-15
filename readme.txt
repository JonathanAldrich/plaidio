//Plaid Bugs

//Bug #rad.1
// Ant cannot build a code containing a string like "c:\\", but it can build one with "c:\\foo".
method main()
{
    val p = "c:\\";
    printLine(p);
}

//Bug #rad.2
// Why does f accept a "String" when its type is explicitly defined?
// This method print "foo".
method main()
{
    val immutable File f = "foo";
    printLine(f);
}

//Bug #rad.3
// Why does p accept a java.lang.String value when its type is explicitly defined?
// And why does it change its type from "String" into "java.lang.String"?
// This method print "P is unknown!".
// Q1: How can we cast a java type object into plaid type object
// (for example cast java.lang.String into plaid.lang.String)?
method main()
{
    val immutable String v = (immutable String) java.io.File.pathSeparator;
	match (v)
	{
		case String	{printLine("v is String!"); }
		default		{ printLine("v is unknown!"); }
	};
}
// This method print "c is String!".
method main()
{
	val immutable Character c = "new String";
	match (c)
	{
		case String		{ printLine("c is String!"); }
		case Character	{ printLine("c is Character!"); }
		default 		{ printLine("c is unknown!"); }
	};
}

//Bug #rad.4
// Q1: In below example, why does "s" contain "getClass" method,
// but "c" does not contain one?
method main()
{
    val s = "string";
    val c = 'c';
    
    printLine(s.getClass());
    printLine(c.getClass());
}

//Bug #rad.5
// Q1: What is the difference between c1 and c2?
method main()
{
    val c1 = new Character { val nativeVal = 'c'; };
    val c2 = new Character with { val nativeVal = 'c'; };
}
