package com.android.list.permission

/**
 * 清单文件读工具类
 */
class FileReadKits {


    /**
     * 动态移除不合法的权限
     * @param manifestContent
     * @param replacePermission
     * @return
     */
    static String replacePermission(String manifestContent, ArrayList<String> filterPermissions) {
        println(manifestContent)
        for (int i = 0; i < filterPermissions.size(); i++) {
            String filterPermissionStr = filterPermissions[i].trim()
            manifestContent = replaceManifestPermission(manifestContent, filterPermissionStr)
        }
        return manifestContent
    }

    static String replaceManifestPermission(String manifestContent, String replacePermission) {
        if (replacePermission == '') {
            return manifestContent
        }
        def index = manifestContent.indexOf(replacePermission)
        if (index < 0) {
            return manifestContent
        }
        def start = -1;
        def end = -1;
        for (int i = index; i > 0; i--) {
            if (manifestContent.charAt(i) == '<') {
                start = i
                break
            }
        }
        for (int i = index; i < manifestContent.length(); i++) {
            if (manifestContent.charAt(i) == '<') {
                end = i
                break
            }
        }
        println("start" + start + "end" + end)
        if (end != -1 && start != -1) {
            manifestContent = manifestContent.replace(manifestContent.substring(start, end), "")
        }
        return manifestContent
    }
    /**
     * 读取文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    static String readTextFile(File file) throws IOException {
        String text = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            text = readTextInputStream(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }

    /**
     * 从流中读取文件
     *
     * @param is
     * @return
     * @throws IOException
     */
    static String readTextInputStream(InputStream is) throws IOException {
        StringBuffer strbuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                strbuffer.append(line).append("\r\n");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return strbuffer.toString();
    }


}