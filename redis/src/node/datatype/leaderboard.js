var redis = require("redis");
var client = redis.createClient();

function LeaderBoard(key) { // 1
    this.key = key; // 2
}

LeaderBoard.prototype.addUser = function (username, score) { // 1
    client.zadd([this.key, score, username], function (err, replies) {// 2
        console.log("User", username, "added to the leaderboard!"); // 3
    });
};

LeaderBoard.prototype.removeUser = function (user) {
    client.zrem(this.key, user, function (err, replies) { // 2
        console.log("User", user, "removed successfully!"); // 3
    });
}

LeaderBoard.prototype.getUserScoreAndRank = function (username) { // 1
    var leaderboardKey = this.key; // 2
    client.zscore(leaderboardKey, username, function (err, zscoreReply) {// 3
        client.zrevrank(leaderboardKey, username, function (err, zrevrankReply) { // 4
            console.log("\nDetails of " + username + ":");
            console.log("Score:", zscoreReply + ", Rank: #" + (zrevrankReply + 1)); // 5
        });
    });
};


LeaderBoard.prototype.showTopUsers = function (quantity) { // 1
    client.zrevrange([this.key, 0, quantity - 1, "WITHSCORES"],
        function (err, reply) { // 2
            console.log("\nTop", quantity, "users:");
            for (var i = 0, rank = 1; i < reply.length; i += 2, rank++) {// 3
                console.log("#" + rank, "User: " + reply[i] + ", score:", reply[i + 1]);
            }
        });
};


board = new LeaderBoard('leaders');
board.addUser("lee", "10");
board.addUser("lee2", "20");
board.addUser("lee3", "120");
board.addUser("lee5", "220");
// board.removeUser("lee");

board.getUserScoreAndRank("lee3");
LeaderBoard.prototype.showTopUsers(2);
client.quit();