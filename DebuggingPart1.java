public class DebuggingPart1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            //System.out.println(index+1);
            //System.out.println(index+4);
            if ((index + 4)>input.length()){
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
    public void test() {
        //findAbc("abcd");
        findAbc("abcdabc");
        findAbc("abcbbbabcdddabc");
        findAbc("aaaaabc");
        findAbc("yabcyabc");
    }
}
