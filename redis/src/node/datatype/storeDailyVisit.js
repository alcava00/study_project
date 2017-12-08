var redis = require("redis");
var client = redis.createClient();

function storeDailyVisit(date, userId) {
    var key = 'visits:daily:' + date; // 3
    client.setbit(key, userId, 1, function (err, reply) {
        console.log("User", userId, "visited on", date);
    });
}


function countVisits(date) {
    var key = 'visits:daily:' + date;
    client.bitcount(key, function (err, reply) {
        console.log(date, "had", reply, "visits."); // 4
    })
}

function makeKey(date) {
    return 'visits:daily:' + date;

}

function showUserIdsFromVisit(date) {
    var key = makeKey(date);

    function showUserIdsFromVisit(date) { // 1
        var key = 'visits:daily:' + date; // 2
        client.get(key, function (err, bitmapValue) { // 3
            var userIds = []; // 4
            var data = bitmapValue.toJSON().data; // 5
            data.forEach(function (byte, byteIndex) { // 6
                for (var bitIndex = 7; bitIndex >= 0; bitIndex--) { // 7
                    var visited = byte >> bitIndex & 1; // 8
                    if (visited === 1) { // 9
                        var userId = byteIndex * 8 + (7 - bitIndex); // 10
                        userIds.push(userId); // 11
                    }
                }
            });
            console.log("Users " + userIds + " visited on " + date); // 12
        });
    }
}

storeDailyVisit(20171206, 1);
storeDailyVisit(20171206, 2);
storeDailyVisit(20171206, 2);

countVisits(20171206);

showUserIdsFromVisit(20171206);

client.quit();