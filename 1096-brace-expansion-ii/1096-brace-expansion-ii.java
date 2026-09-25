class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            } 
            else if (c == '{') {
                int j = i;
                int depth = 0;

                while (j < s.length()) {
                    if (s.charAt(j) == '{') depth++;
                    else if (s.charAt(j) == '}') depth--;

                    if (depth == 0) break;
                    j++;
                }

                Set<String> next = parse(s.substring(i + 1, j));
                current = combine(current, next);
                i = j + 1;
            } 
            else {
                Set<String> next = new HashSet<>();
                next.add(String.valueOf(c));

                current = combine(current, next);
                i++;
            }
        }

        result.addAll(current);
        return result;
    }

    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}