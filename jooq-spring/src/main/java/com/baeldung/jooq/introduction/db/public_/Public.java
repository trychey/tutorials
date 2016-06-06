/**
 * This class is generated by jOOQ
 */
package com.baeldung.jooq.introduction.db.public_;


import com.baeldung.jooq.introduction.db.public_.tables.Author;
import com.baeldung.jooq.introduction.db.public_.tables.AuthorBook;
import com.baeldung.jooq.introduction.db.public_.tables.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

	private static final long serialVersionUID = 1806497150;

	/**
	 * The reference instance of <code>PUBLIC</code>
	 */
	public static final Public PUBLIC = new Public();

	/**
	 * No further instances allowed
	 */
	private Public() {
		super("PUBLIC");
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			Author.AUTHOR,
			Book.BOOK,
			AuthorBook.AUTHOR_BOOK);
	}
}
