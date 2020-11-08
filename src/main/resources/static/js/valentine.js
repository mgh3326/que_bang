function shuffleArray(n) {
    for (let t = n.length - 1; t > 0; t--) {
        let i = Math.floor(Math.random() * (t + 1));
        [n[t], n[i]] = [n[i], n[t]]
    }
    return n
}

function animate(n, t, i) {
    var r = randomNumber(64, 190), u = randomNumber(128, 255), f = randomNumber(128, 255), e = randomNumber(55, 85);
    inArray(i, heart) && (r = randomNumber(192, 255), u = randomNumber(0, 63), f = randomNumber(0, 63), e = randomNumber(70, 100));
    context.fillStyle = "rgba(" + r + "," + u + "," + f + "," + e + ")";
    context.fillRect(n, t, 25, 25)
}

function randomNumber(n, t) {
    return Math.floor(Math.random() * (t - n + 1) + n)
}

function draw() {
    var r, n;
    if (canvas.getContext) {
        nums = Array.matrix(400, 3, 0);
        var i = 0, u = 0, t = 0;
        for (n = 0; n < 20; n++) {
            for (r = 0; r < 20; r++) nums[t][0] = i, nums[t][1] = u, nums[t][2] = t, t += 1, i += 26;
            i = 0;
            u += 26
        }
        for (nums = shuffleArray(nums), n = 0; n < nums.length; n++) doSetTimeout(n)
    }
}

function doSetTimeout(n) {
    setTimeout(function () {
        animate(nums[n][0], nums[n][1], nums[n][2])
    }, 10 * n)
}

function animate(n, t, i) {
    var r = randomNumber(64, 191), u = randomNumber(128, 255), f = randomNumber(128, 255), e = randomNumber(55, 85);
    inArray(i, heart) ? (r = randomNumber(192, 255), u = randomNumber(0, 63), f = randomNumber(0, 63), e = randomNumber(70, 100)) : context.globalAlpha = .6;
    context.fillStyle = "rgba(" + r + "," + u + "," + f + "," + e + ")";
    fillRoundedRect(n, t, 25, 25, 4)
}

function fillRoundedRect(n, t, i, r, u) {
    context.beginPath();
    context;
    context.moveTo(n + u, t);
    context;
    context.lineTo(n + i - u, t);
    context;
    context.quadraticCurveTo(n + i, t, n + i, t + u);
    context;
    context.lineTo(n + i, t + r - u);
    context;
    context.quadraticCurveTo(n + i, t + r, n + i - u, t + r);
    context;
    context.lineTo(n + u, t + r);
    context;
    context.quadraticCurveTo(n, t + r, n, t + r - u);
    context;
    context.lineTo(n, t + u);
    context;
    context.quadraticCurveTo(n, t, n + u, t);
    context;
    context.fill()
}

function inArray(n, t) {
    for (var r = t.length, i = 0; i < r; i++) if (t[i] === n) return !0;
    return !1
}

var heart = [65, 66, 67, 72, 73, 74, 84, 85, 86, 87, 88, 91, 92, 93, 94, 95, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 266, 267, 268, 269, 270, 271, 272, 273, 287, 288, 289, 290, 291, 292, 308, 309, 310, 311, 329, 330],
    nums, canvas = document.getElementById("canvas"), context = canvas.getContext("2d");
Array.matrix = function (n, t, i) {
    for (var f, r, e = [], u = 0; u < n; ++u) {
        for (f = [], r = 0; r < t; ++r) f[r] = i;
        e[u] = f
    }
    return e
};
draw();