// https://www.codewars.com/kata/585894545a8a07255e0002f1/train/javascript

function countPatternsFrom(firstDot, length) {
    let book = {
        A: ['B', 'D', 'E', "F", 'H'],
        B: ['A', 'C', 'D', 'E', 'F', 'G', 'I'],
        C: ['B', 'D', 'E', 'F', 'H'],
        D: ['A', 'B', 'C', 'E', 'G', 'H', 'I'],
        E: ['A', 'B', 'C', 'D', 'F', 'G', 'H', 'I'],
        F: ['A', 'B', 'C', 'E', 'G', 'H', 'I'],
        G: ['B', 'D', 'E', 'F', 'H'],//////////////////
        H: ['A', 'C', 'D', 'E', 'F', 'G', 'I'],
        I: ['B', 'D', 'E', 'F', 'H']
    }
    let passed = [];
    let count = 0;
    if (length == 1) return 1;

    function countPF(firstDot, length) {
        if (firstDot == 'E') { book.A.push('I'); book.I.push('A'); book.G.push('C'); book.C.push('G'); book.H.push('B'); book.B.push('H'); book.D.push('F'); book.F.push('D'); }
        if (firstDot == 'B') { book.A.push('C'); book.C.push('A'); }
        if (firstDot == 'F') { book.I.push('C'); book.C.push('I'); }
        if (firstDot == 'D') { book.A.push('G'); book.G.push('A'); }
        if (firstDot == 'H') { book.I.push('G'); book.G.push('I'); }

        if ((length > 9) || (length == 0)) { return 0; }

        if (passed.indexOf(firstDot) != -1) { return 0 }
        passed.push(firstDot);

        if (length == 1) { count++; passed.pop(); return; }


        book[firstDot].forEach(function (letter) {
            countPF(letter, length - 1); //passed.pop();
            if (letter == 'E') { book.A.pop(); book.I.pop(); book.G.pop(); book.C.pop(); book.H.pop(); book.B.pop(); book.D.pop(); book.F.pop(); }
            if (letter == 'B') {
                book.A.pop();
                book.C.pop();
            }
            if (letter == 'F') { book.I.pop(); book.C.pop(); }
            if (letter == 'D') { book.A.pop(); book.G.pop(); }
            if (letter == 'H') { book.I.pop(); book.G.pop(); }
        });
        passed.pop();
        return count;
    }


    return countPF(firstDot, length);
}
