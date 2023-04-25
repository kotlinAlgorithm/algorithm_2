package src.week7

private lateinit var volumeChanges: List<Int>
private lateinit var maxVolumes: Array<IntArray>
private var volumeLimit = 0

fun main() {
    val (musicCount, startVolume, _volumeLimit) = readln().split(" ").map(String::toInt)
    volumeLimit = _volumeLimit
    volumeChanges = readln().split(" ").map(String::toInt)
    maxVolumes = Array(volumeLimit + 1) {
        IntArray(musicCount) { -2 }
    }
    findMaxVolumes(startVolume, 0).let { print(it) }
}

private fun findMaxVolumes(startVolume: Int, i: Int): Int {
    if (startVolume !in 0..volumeLimit) {
        return -1
    }
    if (i == volumeChanges.size) {
        return startVolume
    }

    if (maxVolumes[startVolume][i] != -2) {
        return maxVolumes[startVolume][i]
    }

    var myMaxVolume = -1
    myMaxVolume = maxOf(myMaxVolume, findMaxVolumes(startVolume - volumeChanges[i], i + 1))
    myMaxVolume = maxOf(myMaxVolume, findMaxVolumes(startVolume + volumeChanges[i], i + 1))

    maxVolumes[startVolume][i] = myMaxVolume
    return myMaxVolume
}