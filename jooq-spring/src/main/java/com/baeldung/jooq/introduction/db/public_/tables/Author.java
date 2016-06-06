/**
 * This class is generated by jOOQ
 */
package com.baeldung.jooq.introduction.db.public_.tables;


import com.baeldung.jooq.introduction.db.public_.Keys;
import com.baeldung.jooq.introduction.db.public_.Public;
import com.baeldung.jooq.introduction.db.public_.tables.records.AuthorRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Author extends TableImpl<AuthorRecord> {

	private static final long serialVersionUID = 1121046490;

	/**
	 * The reference instance of <code>PUBLIC.AUTHOR</code>
	 */
	public static final Author AUTHOR = new Author();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<AuthorRecord> getRecordType() {
		return AuthorRecord.class;
	}

	/**
	 * The column <code>PUBLIC.AUTHOR.ID</code>.
	 */
	public final TableField<AuthorRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>PUBLIC.AUTHOR.FIRST_NAME</code>.
	 */
	public final TableField<AuthorRecord, String> FIRST_NAME = createField("FIRST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>PUBLIC.AUTHOR.LAST_NAME</code>.
	 */
	public final TableField<AuthorRecord, String> LAST_NAME = createField("LAST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * Create a <code>PUBLIC.AUTHOR</code> table reference
	 */
	public Author() {
		this("AUTHOR", null);
	}

	/**
	 * Create an aliased <code>PUBLIC.AUTHOR</code> table reference
	 */
	public Author(String alias) {
		this(alias, AUTHOR);
	}

	private Author(String alias, Table<AuthorRecord> aliased) {
		this(alias, aliased, null);
	}

	private Author(String alias, Table<AuthorRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<AuthorRecord> getPrimaryKey() {
		return Keys.CONSTRAINT_7;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<AuthorRecord>> getKeys() {
		return Arrays.<UniqueKey<AuthorRecord>>asList(Keys.CONSTRAINT_7);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Author as(String alias) {
		return new Author(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Author rename(String name) {
		return new Author(name, null);
	}
}
