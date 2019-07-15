package chapter8;


public class otherPeople {

    public static void main(String[] args) {

        int[] numbers = {19, 36, 24, 10, 9, 29, 1, 0, 3, 60, 100, 1001, 999, 520, 123, 96};

        radixSort(numbers);

        for (int number : numbers) {

            System.out.println(number);

        }

        String[] words = {"Java", "Mongodb", "Redis", "Kafka", "javascript", "mysql", "mybatis", "kindle", "rpc", "Algorithm", "mergeSort", "quickSort", "Adobe"};
        radixSort(words);

        for (String word : words) {
            System.out.println(word.replaceAll("0", ""));
        }

    }

    /**
     * @Author: xingrui
     * @Description: 基数排序（单词）
     * @Date: 15:53 2019/1/30
     */

    private static void radixSort(String[] words) {

        int exp = 0;

        int maxLength = getMaxLength(words);

        autoComplete(words, maxLength);

        for (exp = 1; exp <= maxLength; exp++) {

            countingSort(words, exp);

        }

    }

    /**
     * @Author: xingrui
     * @Description: 计数排序（单词）
     * @Date: 13:57 2019/1/30
     */

    private static void countingSort(String[] words, int exp) {

        int n = words.length;

        String[] r = new String[n];

        int[] c = new int[122];

        for (int i = 0; i < n; ++i) {

            int asc = (byte) words[i].charAt(words[i].length() - exp);

            c[asc]++;

        }

        for (int i = 1; i < 122; ++i) {

            c[i] = c[i - 1] + c[i];

        }

        for (int i = n - 1; i >= 0; --i) {

            int asc = (byte) words[i].charAt(words[i].length() - exp);

            int index = c[asc];

            r[index - 1] = words[i];

            c[asc]--;

        }

        for (int i = 0; i < n; ++i) {

            words[i] = r[i];

        }

    }

    /**
     * @Author: xingrui
     * @Description: 基数排序（纯数字）
     * @Date: 15:00 2019/1/30
     */

    private static void radixSort(int[] numbers) {

        int exp = 0;

        int maxNumber = getMaxNumber(numbers);

        for (exp = 1; maxNumber / exp > 0; exp *= 10) {

            countingSort(numbers, exp);

        }

    }

    /**
     * @Author: xingrui
     * @Description: 计数排序（纯数字）
     * @Date: 13:57 2019/1/30
     */

    private static void countingSort(int[] numbers, int exp) {

        int n = numbers.length;

        int[] r = new int[n];

        int[] c = new int[10];

        for (int i = 0; i < n; ++i) {

            c[numbers[i] / exp % 10]++;

        }

        for (int i = 1; i < 10; ++i) {

            c[i] = c[i - 1] + c[i];

        }

        for (int i = n - 1; i >= 0; --i) {

            int index = c[numbers[i] / exp % 10];

            r[index - 1] = numbers[i];

            c[numbers[i] / exp % 10]--;

        }

        for (int i = 0; i < n; ++i) {

            numbers[i] = r[i];

        }

    }

    /**
     * @Author: xingrui
     * @Description: 自动补全单词
     * @Date: 16:38 2019/1/30
     */

    private static void autoComplete(String[] words, int maxLength) {

        int i = 0;

        for (String word : words) {

            if (word.length() < maxLength) {

                int value = maxLength - word.length();

                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < value; ++j) {

                    sb.append("0");

                }

                words[i] = word + sb;

            }

            i++;

        }

    }

    /**
     * @Author: xingrui
     * @Description: 获取字符串最大的长度
     * @Date: 15:56 2019/1/30
     */

    private static int getMaxLength(String[] words) {

        int maxLength = words[0].length();

        for (int i = 1; i < words.length; ++i) {

            if (words[i].length() > maxLength)

                maxLength = words[i].length();

        }

        return maxLength;

    }

    /**
     * @Author: xingrui
     * @Description: 获取最大的数字
     * @Date: 15:56 2019/1/30
     */

    private static int getMaxNumber(int[] numbers) {

        int maxNumber = numbers[0];

        for (int i = 1; i < numbers.length; ++i) {

            if (numbers[i] > maxNumber)

                maxNumber = numbers[i];

        }

        return maxNumber;

    }

}