/**
 * This class is generated by jOOQ
 */
package com.baeldung.jooq.introduction.db.information_schema.tables.records;


import com.baeldung.jooq.introduction.db.information_schema.tables.Rights;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.TableRecordImpl;


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
public class RightsRecord extends TableRecordImpl<RightsRecord> implements Record7<String, String, String, String, String, String, Integer> {

	private static final long serialVersionUID = 269610816;

	/**
	 * Setter for <code>INFORMATION_SCHEMA.RIGHTS.GRANTEE</code>.
	 */
	public void setGrantee(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.RIGHTS.GRANTEE</code>.
	 */
	public String getGrantee() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.RIGHTS.GRANTEETYPE</code>.
	 */
	public void setGranteetype(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.RIGHTS.GRANTEETYPE</code>.
	 */
	public String getGranteetype() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.RIGHTS.GRANTEDROLE</code>.
	 */
	public void setGrantedrole(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.RIGHTS.GRANTEDROLE</code>.
	 */
	public String getGrantedrole() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.RIGHTS.RIGHTS</code>.
	 */
	public void setRights(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.RIGHTS.RIGHTS</code>.
	 */
	public String getRights() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.RIGHTS.TABLE_SCHEMA</code>.
	 */
	public void setTableSchema(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.RIGHTS.TABLE_SCHEMA</code>.
	 */
	public String getTableSchema() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.RIGHTS.TABLE_NAME</code>.
	 */
	public void setTableName(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.RIGHTS.TABLE_NAME</code>.
	 */
	public String getTableName() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.RIGHTS.ID</code>.
	 */
	public void setId(Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.RIGHTS.ID</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(6);
	}

	// -------------------------------------------------------------------------
	// Record7 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<String, String, String, String, String, String, Integer> fieldsRow() {
		return (Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<String, String, String, String, String, String, Integer> valuesRow() {
		return (Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Rights.RIGHTS.GRANTEE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Rights.RIGHTS.GRANTEETYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Rights.RIGHTS.GRANTEDROLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Rights.RIGHTS.RIGHTS_;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Rights.RIGHTS.TABLE_SCHEMA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return Rights.RIGHTS.TABLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field7() {
		return Rights.RIGHTS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getGrantee();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getGranteetype();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getGrantedrole();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getRights();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getTableSchema();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getTableName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value7() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord value1(String value) {
		setGrantee(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord value2(String value) {
		setGranteetype(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord value3(String value) {
		setGrantedrole(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord value4(String value) {
		setRights(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord value5(String value) {
		setTableSchema(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord value6(String value) {
		setTableName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord value7(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RightsRecord values(String value1, String value2, String value3, String value4, String value5, String value6, Integer value7) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached RightsRecord
	 */
	public RightsRecord() {
		super(Rights.RIGHTS);
	}

	/**
	 * Create a detached, initialised RightsRecord
	 */
	public RightsRecord(String grantee, String granteetype, String grantedrole, String rights, String tableSchema, String tableName, Integer id) {
		super(Rights.RIGHTS);

		setValue(0, grantee);
		setValue(1, granteetype);
		setValue(2, grantedrole);
		setValue(3, rights);
		setValue(4, tableSchema);
		setValue(5, tableName);
		setValue(6, id);
	}
}
