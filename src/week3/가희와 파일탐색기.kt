const val NO_PERMISSION = 0
const val ONLY_EXECUTION = 1
const val ONLY_WRITE = 2
const val EXECUTION_AND_WRITE = 3
const val ONLY_READ = 4
const val READ_AND_EXECUTION = 5
const val READ_AND_WRITE = 6
const val ALL_PERMISSION = 7

const val TYPE_GROUP = 30
const val TYPE_USER = 40
const val TYPE_OTHERS = 50

data class User(
    val name: String,
    val groupList: HashMap<String, String>
)

data class File(
    val name: String,
    val permissionList: MutableList<FilePermissionListItem>
)

data class FilePermissionListItem(
    val type: Int,
    val name: String,
    val permission: Int
)

val userList: HashMap<String, User> = hashMapOf()
val fileList: HashMap<String, File> = hashMapOf()


fun main() {
    val (userNum, fileInfoNum) = readln().split(" ").map { it.toInt() }

    for (i in 0 until userNum) {
        val input = readln().split(" ")
        userList[input[0]] = User(input[0], hashMapOf(input[0] to input[0]))

        if (input.size > 1) {
            val groups = input[1].split(",")
            for (j in groups.indices) {
                userList[input[0]]!!.groupList[groups[j]] = groups[j]
            }
        }

    }

    for (i in 0 until fileInfoNum) {
        val input = readln().split(" ")
        fileList[input[0]] = File(
            input[0], mutableListOf(
                FilePermissionListItem(TYPE_USER, input[2], input[1][0].digitToInt()),
                FilePermissionListItem(TYPE_GROUP, input[3], input[1][1].digitToInt()),
                FilePermissionListItem(TYPE_OTHERS, "", input[1][2].digitToInt())
            )
        )
    }

    val commandNum = readln().toInt()
    for (i in 0 until commandNum) {
        val input = readln().split(" ")
        //user먼저 확인.
        if (fileList[input[1]]!!.permissionList[0].name == input[0]) {
            if (checkPermission(input[2], fileList[input[1]]!!.permissionList[0].permission)) {
                println(1)
                continue
            }
        }

        //그 다음 그룹 확인.
        if (userList[input[0]]!!.groupList[fileList[input[1]]!!.permissionList[1].name] != null) {//이게 시간초과같은데
            if (checkPermission(input[2], fileList[input[1]]!!.permissionList[1].permission)) {
                println(1)
                continue
            }
        }

        //마지막으로 others확인
        if (checkPermission(input[2], fileList[input[1]]!!.permissionList[2].permission)) {
            println(1)
            continue
        }

        println(0)
    }

}

fun checkPermission(command: String, permission: Int): Boolean {
    when (command) {
        "X" -> {
            return permission % 2 == 1
        }

        "W" -> {
            return permission == ONLY_WRITE || permission == EXECUTION_AND_WRITE || permission == READ_AND_WRITE || permission == ALL_PERMISSION
        }

        "R" -> {
            return permission == ONLY_READ || permission == READ_AND_EXECUTION || permission == READ_AND_WRITE || permission == ALL_PERMISSION
        }
    }

    return true
}