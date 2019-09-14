package com.android.list.permission

import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 *
 * 权限清理插件
 *
 */
class PermissionPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.afterEvaluate {
            project.android.applicationVariants.all { variant ->
                variant.outputs.each { output ->
                    output.processResourcesProvider.get().doFirst { pm ->
                        String manifestPath = output.processResources.manifestFile
                        println(manifestPath)
                        def manifestContent = project.file(manifestPath).getText()
                        File permissionFile = new File(project.projectDir, "permissionList.txt")

                        println("清单文件内容" + manifestContent)
                        println("过滤文件路径" + permissionFile.getAbsolutePath())
                        ArrayList<String> filterPermissions = new ArrayList<>()
                        def permissionContent = FileReadKits.readTextFile(permissionFile)
                        println("过滤文件内容" + permissionContent)

                        permissionFile.readLines('utf-8').each { lines ->
                            if (lines == null || lines.trim().equalsIgnoreCase("") || lines.startsWith("#")) {
                                return
                            }
                            filterPermissions.add(lines)
                        }
                        manifestContent = FileReadKits.replacePermission(manifestContent, filterPermissions)
                        println("最终输出结果" + manifestContent)
                        project.file(manifestPath).write(manifestContent)
                    }
                }
            }
        }
    }
}

