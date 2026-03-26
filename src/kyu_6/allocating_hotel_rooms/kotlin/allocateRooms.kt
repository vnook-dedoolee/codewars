// https://www.codewars.com/kata/6638277786032a014d3e0072/train/kotlin

package kyu_6.allocating_hotel_rooms.kotlin

fun allocateRooms(customers: Array<IntArray>): IntArray {
    val n = customers.size
    val result = IntArray(n)
    val rooms = mutableListOf<Int>()

    val sortedCustomers = customers.mapIndexed { index, (arrival, _) ->
        arrival to index
    }.sortedBy { it.first }

    for ((arrival, originalIndex) in sortedCustomers) {
        val departure = customers[originalIndex][1]
        var assignedRoom = -1

        for (i in rooms.indices) {
            if (rooms[i] < arrival) {
                assignedRoom = i
                rooms[i] = departure
                break
            }
        }

        if (assignedRoom == -1) {
            assignedRoom = rooms.size
            rooms.add(departure)
        }

        result[originalIndex] = assignedRoom + 1
    }

    return result
}