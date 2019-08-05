package com.aiyi.core.util.hibernate.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;


public final class StringHelper {
    @SuppressWarnings("unused")
    private static final int ALIAS_TRUNCATE_LENGTH = 10;
    public static final String WHITESPACE = " \n\r\f\t";

    public static int lastIndexOfLetter(String string) {
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (!Character.isLetter(character)) return i - 1;
        }
        return string.length() - 1;
    }

    public static String join(String seperator, String[] strings) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuffer buf = new StringBuffer(length * strings[0].length())
                .append(strings[0]);
        for (int i = 1; i < length; i++) {
            buf.append(seperator).append(strings[i]);
        }
        return buf.toString();
    }

    public static String join(String seperator, Iterator<?> objects) {
        StringBuffer buf = new StringBuffer();
        if (objects.hasNext()) buf.append(objects.next());
        while (objects.hasNext()) {
            buf.append(seperator).append(objects.next());
        }
        return buf.toString();
    }

    public static String[] add(String[] x, String sep, String[] y) {
        String[] result = new String[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = (x[i] + sep + y[i]);
        }
        return result;
    }

    public static String repeat(String string, int times) {
        StringBuffer buf = new StringBuffer(string.length() * times);
        for (int i = 0; i < times; i++) buf.append(string);
        return buf.toString();
    }

    public static String repeat(char character, int times) {
        char[] buffer = new char[times];
        Arrays.fill(buffer, character);
        return new String(buffer);
    }

    public static String replace(String template, String placeholder, String replacement) {
        return replace(template, placeholder, replacement, false);
    }

    public static String[] replace(String[] templates, String placeholder, String replacement) {
        String[] result = new String[templates.length];
        for (int i = 0; i < templates.length; i++) {
            result[i] = replace(templates[i], placeholder, replacement);
        }
        return result;
    }

    public static String replace(String template, String placeholder, String replacement, boolean wholeWords) {
        if (template == null) {
            return template;
        }
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        }

        boolean actuallyReplace = (!wholeWords) ||
                (loc + placeholder.length() == template.length()) ||
                (!Character.isJavaIdentifierPart(template.charAt(loc + placeholder.length())));
        String actualReplacement = actuallyReplace ? replacement : placeholder;
        return template.substring(0, loc) +
                actualReplacement +
                replace(template.substring(loc + placeholder.length()),
                        placeholder,
                        replacement,
                        wholeWords);
    }

    public static String replaceOnce(String template, String placeholder, String replacement) {
        if (template == null) {
            return template;
        }
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        }

        return template.substring(0, loc) +
                replacement +
                template.substring(loc + placeholder.length());
    }

    public static String[] split(String seperators, String list) {
        return split(seperators, list, false);
    }

    public static String[] split(String seperators, String list, boolean include) {
        StringTokenizer tokens = new StringTokenizer(list, seperators, include);
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            result[(i++)] = tokens.nextToken();
        }
        return result;
    }

    public static String unqualify(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        return loc < 0 ? qualifiedName : qualifiedName.substring(loc + 1);
    }

    public static String qualifier(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        return loc < 0 ? "" : qualifiedName.substring(0, loc);
    }

    public static String collapse(String name) {
        if (name == null) {
            return null;
        }
        int breakPoint = name.lastIndexOf('.');
        if (breakPoint < 0) {
            return name;
        }
        return collapseQualifier(name.substring(0, breakPoint), true) + name.substring(breakPoint);
    }

    public static String collapseQualifier(String qualifier, boolean includeDots) {
        StringTokenizer tokenizer = new StringTokenizer(qualifier, ".");
        String collapsed = Character.toString(tokenizer.nextToken().charAt(0));
        while (tokenizer.hasMoreTokens()) {
            if (includeDots) {
                collapsed = collapsed + '.';
            }
            collapsed = collapsed + tokenizer.nextToken().charAt(0);
        }
        return collapsed;
    }

    public static String partiallyUnqualify(String name, String qualifierBase) {
        if ((name == null) || (!name.startsWith(qualifierBase))) {
            return name;
        }
        return name.substring(qualifierBase.length() + 1);
    }

    public static String collapseQualifierBase(String name, String qualifierBase) {
        if ((name == null) || (!name.startsWith(qualifierBase))) {
            return collapse(name);
        }
        return collapseQualifier(qualifierBase, true) + name.substring(qualifierBase.length());
    }

    public static String[] suffix(String[] columns, String suffix) {
        if (suffix == null) {
            return columns;
        }
        String[] qualified = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            qualified[i] = suffix(columns[i], suffix);
        }
        return qualified;
    }

    private static String suffix(String name, String suffix) {
        return name + suffix;
    }

    public static String root(String qualifiedName) {
        int loc = qualifiedName.indexOf(".");
        return loc < 0 ? qualifiedName : qualifiedName.substring(0, loc);
    }

    public static String unroot(String qualifiedName) {
        int loc = qualifiedName.indexOf(".");
        return loc < 0 ? qualifiedName : qualifiedName.substring(loc + 1, qualifiedName.length());
    }

    public static boolean booleanValue(String tfString) {
        String trimmed = tfString.trim().toLowerCase();
        return (trimmed.equals("true")) || (trimmed.equals("t"));
    }

    public static String toString(Object[] array) {
        int len = array.length;
        if (len == 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer(len * 12);
        for (int i = 0; i < len - 1; i++) {
            buf.append(array[i]).append(", ");
        }
        return String.valueOf(array[(len - 1)]);
    }

    public static String[] multiply(String string, Iterator<?> placeholders, Iterator<?> replacements) {
        String[] result = {string};
        while (placeholders.hasNext()) {
            result = multiply(result, (String) placeholders.next(), (String[]) replacements.next());
        }
        return result;
    }

    private static String[] multiply(String[] strings, String placeholder, String[] replacements) {
        String[] results = new String[replacements.length * strings.length];
        int n = 0;
        for (int i = 0; i < replacements.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                results[(n++)] = replaceOnce(strings[j], placeholder, replacements[i]);
            }
        }
        return results;
    }

    public static int countUnquoted(String string, char character) {
        if ('\'' == character) {
            throw new IllegalArgumentException("Unquoted count of quotes is invalid");
        }
        if (string == null) {
            return 0;
        }

        int count = 0;
        int stringLength = string.length();
        boolean inQuote = false;
        for (int indx = 0; indx < stringLength; indx++) {
            char c = string.charAt(indx);
            if (inQuote) {
                if ('\'' == c) {
                    inQuote = false;
                }
            } else if ('\'' == c) {
                inQuote = true;
            } else if (c == character) {
                count++;
            }
        }
        return count;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static int[] locateUnquoted(String string, char character) {
        if ('\'' == character) {
            throw new IllegalArgumentException("Unquoted count of quotes is invalid");
        }
        if (string == null) {
            return new int[0];
        }

        ArrayList locations = new ArrayList(20);

        int stringLength = string.length();
        boolean inQuote = false;
        for (int indx = 0; indx < stringLength; indx++) {
            char c = string.charAt(indx);
            if (inQuote) {
                if ('\'' == c) {
                    inQuote = false;
                }
            } else if ('\'' == c) {
                inQuote = true;
            } else if (c == character) {
                locations.add(new Integer(indx));
            }
        }
        return ArrayHelper.toIntArray(locations);
    }

    public static boolean isNotEmpty(String string) {
        return (string != null) && (string.length() > 0);
    }

    public static boolean isEmpty(String string) {
        return (string == null) || (string.length() == 0);
    }

    public static String qualify(String prefix, String name) {
        if ((name == null) || (prefix == null)) {
            throw new NullPointerException();
        }
        return prefix.length() + name.length() + 1 +
                prefix +
                '.' +
                name;
    }

    public static String[] qualify(String prefix, String[] names) {
        if (prefix == null) {
            return names;
        }
        int len = names.length;
        String[] qualified = new String[len];
        for (int i = 0; i < len; i++) {
            qualified[i] = qualify(prefix, names[i]);
        }
        return qualified;
    }

    public static int firstIndexOfChar(String sqlString, String string, int startindex) {
        int matchAt = -1;
        for (int i = 0; i < string.length(); i++) {
            int curMatch = sqlString.indexOf(string.charAt(i), startindex);
            if (curMatch >= 0) {
                if (matchAt == -1) {
                    matchAt = curMatch;
                } else {
                    matchAt = Math.min(matchAt, curMatch);
                }
            }
        }
        return matchAt;
    }

    public static String truncate(String string, int length) {
        if (string.length() <= length) {
            return string;
        }

        return string.substring(0, length);
    }

    public static String generateAlias(String description) {
        return generateAliasRoot(description) + '_';
    }

    public static String generateAlias(String description, int unique) {
        return generateAliasRoot(description) +
                Integer.toString(unique) +
                '_';
    }

    private static String generateAliasRoot(String description) {
        String result = truncate(unqualifyEntityName(description), 10)
                .toLowerCase()
                .replace('/', '_')
                .replace('$', '_');
        result = cleanAlias(result);
        if (Character.isDigit(result.charAt(result.length() - 1))) {
            return result + "x";
        }

        return result;
    }

    private static String cleanAlias(String alias) {
        char[] chars = alias.toCharArray();

        if (!Character.isLetter(chars[0])) {
            for (int i = 1; i < chars.length; i++) {
                if (Character.isLetter(chars[i])) {
                    return alias.substring(i);
                }
            }
        }
        return alias;
    }

    public static String unqualifyEntityName(String entityName) {
        String result = unqualify(entityName);
        int slashPos = result.indexOf('/');
        if (slashPos > 0) {
            result = result.substring(0, slashPos - 1);
        }
        return result;
    }

    public static String toUpperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    public static String moveAndToBeginning(String filter) {
        if (filter.trim().length() > 0) {
            filter = filter + " and ";
            if (filter.startsWith(" and ")) filter = filter.substring(4);
        }
        return filter;
    }
}