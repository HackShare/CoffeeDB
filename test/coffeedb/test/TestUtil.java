package coffeedb.test;

import java.util.List;

import coffeedb.*;

import static org.junit.Assert.*;
import coffeedb.operators.ScanOperator;
import coffeedb.types.*;

public class TestUtil {
	public static Schema getSimpleSchema() {
		String[] names = new String[1];
		names[0] = "test";
		
		Type[] types = new Type[1];
		types[0] = Type.getIntType();

		return new Schema(names, types);
	}

	public static Table getSimpleTable(String tableName) {
		return new Table(tableName, getSimpleSchema());
	}

	public static Table createSimpleTable(String tableName) {
		Catalog catalog = CoffeeDB.catalog();
		Table table = getSimpleTable(tableName);
		catalog.addTable(table);
		return table;
	}

	public static Tuple createSimpleTuple() {
		Value[] values = Value.createValueArray(10);
		Schema schema = new Schema();
		schema.addColumn("intVal", Type.getIntType());
		return new Tuple(schema, values);
	}

	public static Tuple createSimpleTuple(Schema schema) {
		Tuple testTuple = new Tuple(schema);
		for (int i = 0; i < schema.numberOfColumns(); i++) {
			Type schemaType = schema._columnTypes.get(i);
			testTuple.setValue(i, createRandomValue(schemaType));
		}

		return testTuple;
	}

	private static Value createRandomValue(Type schemaType) {
		Value value = new Value(schemaType);
		switch (schemaType.getEnum()) {
		case INTEGER: {
			value.setInt(20);
			break;
		}
		default:
			assert (false);
			break;
		}

		return value;
	}

	public static boolean tableExists(String tableName) {
		return CoffeeDB.getInstance().getCatalog().tableExists(tableName);
	}
	
	public static boolean tupleExist(List<Tuple> data, Tuple expected) {
		for (Tuple t : data) {
			if (t.equals(expected)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void tuplesExist(List<Tuple> data, List<Tuple> expected) {
		for (Tuple t : expected) {
			assertTrue(tupleExist(data, t));
		}
	}
	
	public static boolean tupleExists(String tableName, Tuple tuple) {
		if (!tableExists(tableName))
			return false;

		ScanOperator scan = new ScanOperator(tableName);
		for (Tuple t : scan.getData()) {
			if (t.equals(tuple))
				return true;
		}

		return false;
	}
	
	public static void assertColumnIsValue(String tableName, String column, Value value) {
		ScanOperator scan = new ScanOperator(tableName);
		for (Tuple t : scan.getData()) {
			assertTrue(t.getValue(column).equals(value));
		}
	}
	
	public static void reset() {
		CoffeeDB database = CoffeeDB.getInstance();
		database.reset();
	}
}
