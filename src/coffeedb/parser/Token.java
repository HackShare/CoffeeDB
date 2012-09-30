package coffeedb.parser;

/***
 * SQL Keywords
 * @author masonchang
 */
public enum Token {
	// Keywords
	SELECT, 
	INSERT,
	UPDATE,
	FROM,
	WHERE,
	GROUP,
	BY,
	CREATE,
	INTO,
	VALUES,
	SET,
	
	// Symbols
	LEFT_PAREN,
	RIGHT_PAREN,
	IDENT,
	ASTERIK, 
	COMMA, 
	SEMI_COLON,
	EQUALS,
	
	// Types
	INT,
	STRING,
	VARCHAR,
	BLOB, 
	LONG, 
	DOUBLE,
	EOF, 
};