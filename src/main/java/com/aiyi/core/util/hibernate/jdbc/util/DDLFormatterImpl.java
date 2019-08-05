package com.aiyi.core.util.hibernate.jdbc.util;


import java.util.StringTokenizer;

/**
 * @author gsk (from hibernate source)
 */
public class DDLFormatterImpl implements Formatter {

    public String format(String sql) {
        if (sql.toLowerCase().startsWith("create table")) {
            return formatCreateTable(sql);
        }
        if (sql.toLowerCase().startsWith("alter table")) {
            return formatAlterTable(sql);
        }
        if (sql.toLowerCase().startsWith("comment on")) {
            return formatCommentOn(sql);
        }
        return "\n    " + sql;
    }

    private String formatCommentOn(String sql) {
        StringBuffer result = new StringBuffer(60).append("\n    ");
        StringTokenizer tokens = new StringTokenizer(sql, " '[]\"", true);

        boolean quoted = false;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            result.append(token);
            if (isQuote(token)) {
                quoted = !quoted;
            } else if ((!quoted) &&
                    ("is".equals(token))) {
                result.append("\n       ");
            }

        }

        return result.toString();
    }

    private String formatAlterTable(String sql) {
        StringBuffer result = new StringBuffer(60).append("\n    ");
        StringTokenizer tokens = new StringTokenizer(sql, " (,)'[]\"", true);

        boolean quoted = false;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (isQuote(token)) {
                quoted = !quoted;
            } else if ((!quoted) &&
                    (isBreak(token))) {
                result.append("\n        ");
            }

            result.append(token);
        }

        return result.toString();
    }

    private String formatCreateTable(String sql) {
        StringBuffer result = new StringBuffer(60).append("\n    ");
        StringTokenizer tokens = new StringTokenizer(sql, "(,)'[]\"", true);

        int depth = 0;
        boolean quoted = false;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (isQuote(token)) {
                quoted = !quoted;
                result.append(token);
            } else if (quoted) {
                result.append(token);
            } else {
                if (")".equals(token)) {
                    depth--;
                    if (depth == 0) {
                        result.append("\n    ");
                    }
                }
                result.append(token);
                if ((",".equals(token)) && (depth == 1)) {
                    result.append("\n       ");
                }
                if ("(".equals(token)) {
                    depth++;
                    if (depth == 1) {
                        result.append("\n        ");
                    }
                }
            }
        }

        return result.toString();
    }

    private static boolean isBreak(String token) {
        return ("drop".equals(token)) ||
                ("add".equals(token)) ||
                ("references".equals(token)) ||
                ("foreign".equals(token)) ||
                ("on".equals(token));
    }

    private static boolean isQuote(String tok) {
        return ("\"".equals(tok)) ||
                ("`".equals(tok)) ||
                ("]".equals(tok)) ||
                ("[".equals(tok)) ||
                ("'".equals(tok));
    }
}