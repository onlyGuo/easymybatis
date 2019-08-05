package com.aiyi.core.util.hibernate.jdbc.util;

/**
 * @author gsk (from hibernate source)
 */
public class FormatStyle {

    public static final FormatStyle BASIC = new FormatStyle("basic", new BasicFormatterImpl());
    public static final FormatStyle DDL = new FormatStyle("ddl", new DDLFormatterImpl());
    public static final FormatStyle NONE = new FormatStyle("none", new NoFormatImpl());
    private final String name;
    private final Formatter formatter;

    private FormatStyle(String name, Formatter formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    public String getName() {
        return this.name;
    }

    public Formatter getFormatter() {
        return this.formatter;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        FormatStyle that = (FormatStyle) o;

        return this.name.equals(that.name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    private static class NoFormatImpl implements Formatter {
        public String format(String source) {
            return source;
        }
    }
}