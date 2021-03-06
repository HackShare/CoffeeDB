package coffeedb;

import java.util.ArrayList;
import coffeedb.operators.*;

public class QueryPlan {
	public ArrayList<Operator> _operators;
	private Transaction _transaction;
	
	public QueryPlan() {
		_operators = new ArrayList<Operator>();
	}
	
	public ArrayList<Operator> getOperators() {
		return _operators;
	}
	
	public void addOperator(Operator op) {
		_operators.add(0, op);
	}

	public ScanOperator addSelect(String tableName, ArrayList<String> columns) {
		ScanOperator scan = new ScanOperator(tableName);
		addOperator(scan);
		return scan;
	}
	
	public void addCreate(String tableName, Schema tableSchema) {
		CreateTableOperator createOp = new CreateTableOperator(tableName, tableSchema);
		addOperator(createOp);
	}

	public void createInsertOperator(String tableName, ArrayList<Value> _values) {
		Table table = CoffeeDB.getInstance().getCatalog().getTable(tableName);
		Tuple tuple = new Tuple(table.getSchema(), _values);
		InsertOperator insert = new InsertOperator(tableName, tuple);
		addOperator(insert);
	}
	
	public Transaction getTransaction() {
		return _transaction;
	}

	public void setTransaction(Transaction transaction) {
		this._transaction = transaction;
	}
}
