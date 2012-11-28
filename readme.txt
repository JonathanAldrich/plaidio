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

//Question #rad.5
// Q: What is the difference between c1 and c2?
method main()
{
    val c1 = new Character { val nativeVal = 'c'; };
    val c2 = new Character with { val nativeVal = 'c'; };
}

//Bug #rad.6
// Why cannot parser compile below code becasue of "/**/"?
method main()
{
	/**/
    val c1 = new Character { val nativeVal = 'c'; };
    val c2 = new Character with { val nativeVal = 'c'; };
}

//Question #rad.7
// Q: How can specify conditional transition?
// For example the following transition goes into either Valid or Invalid states.
method validate() [unique Path >> unique Valid | unique Invalid this]


//Question #rad.8
// The result of executing this commit is:
//>>> BEGIN ------------------------------------
//--- NAME: TC-ST-112121112 make a non-existent path and create it as a file.
//START checkValidity
//START checkExistency
//START checkCreatability
//START checkRelativity
//START checkNonRelativity
//START transition to Canonical
//END transition to Canonical
//'D:\Plaid\Workspace\plaid-lang\plaidio\foo'
//The object is Path!
//The object is Valid!
//The object has ExistencyDimension!
//The object is NOT Existent!
//The object is NonExistent!
//The object has NxPermissionDimension!
//The object has CreatabilityDimension!
//The object is Creatable!
//The object is NOT NonCreatable!
//The path is going to be created as a file!
//START checkValidity
//START checkExistency
//START checkCreatability
//START checkRelativity
//START checkNonRelativity
//START transition to Canonical
//plaid.runtime.PlaidRuntimeException: <UNKNOWN>@-1:-1 => Member "stabilizeNonRelative" already defined.
//	at plaid.runtime.models.map.PlaidObjectMap.addMember(PlaidObjectMap.java:167)
//	at plaid.runtime.models.map.PlaidObjectMap.restrictedUpdate(PlaidObjectMap.java:460)
//	at plaid.runtime.models.map.PlaidObjectMap.changeState(PlaidObjectMap.java:349)
//	at plaidio.filesystem.path.valid.relativity.NonRelative$2$1.invoke(NonRelative.java:191)
//	at plaid.runtime.models.map.PlaidFunctionMap.invoke(PlaidFunctionMap.java:46)
//	at plaid.runtime.models.map.PlaidRuntimeMap.call(PlaidRuntimeMap.java:68)
//	at plaid.runtime.Util.call(Util.java:199)
//	at plaidio.filesystem.path.valid.existency.nonexistent.nxpermission.creatability.Creatable$2.invoke(Creatable.java:137)
//	at plaid.runtime.models.map.PlaidMethodMap.invoke(PlaidMethodMap.java:44)
//	at plaid.runtime.models.map.PlaidRuntimeMap.call(PlaidRuntimeMap.java:63)
//	at plaid.runtime.Util.call(Util.java:199)
//	at plaidio.filesystem.path.valid.existency.nonexistent.nxpermission.creatability.test_creatability$1$2$1$1.invoke(test_creatability.java:856)
//	at plaid.runtime.models.map.PlaidFunctionMap.invoke(PlaidFunctionMap.java:46)
//	at plaid.runtime.models.map.PlaidRuntimeMap.call(PlaidRuntimeMap.java:63)
//	at plaid.runtime.Util.call(Util.java:199)
//	at com.akefirad.plaid.testing.TestCase$2$1.invoke(TestCase.java:179)
//	at plaid.runtime.models.map.PlaidFunctionMap.invoke(PlaidFunctionMap.java:46)
//	at plaid.runtime.models.map.PlaidRuntimeMap.call(PlaidRuntimeMap.java:68)
//	at plaid.runtime.Util.call(Util.java:199)
//	at com.akefirad.plaid.testing.TestBed$2$2$2.invoke(TestBed.java:319)
//	at plaid.runtime.models.map.PlaidFunctionMap.invoke(PlaidFunctionMap.java:46)
//	at plaid.runtime.models.map.PlaidRuntimeMap.call(PlaidRuntimeMap.java:63)
//	at plaid.runtime.Util.call(Util.java:199)
//	at plaid.lang.While$1.invoke(While.java:140)
//	at plaid.runtime.models.map.PlaidMethodMap.invoke(PlaidMethodMap.java:44)
//	at plaid.runtime.models.map.PlaidRuntimeMap.call(PlaidRuntimeMap.java:68)
//	at plaid.runtime.Util.call(Util.java:199)
//	at plaidio.main.main(main.java:92)
