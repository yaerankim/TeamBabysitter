// 002
console.log('hello'); // 디버깅하거나 로그를 남길 때 사용

// 003
// ES5
function printHelloEs5() {
    console.log('hello es5');
}
// ES6
const printHelloEs6 = () => {
    console.log('hello es6');
}
printHelloEs5();
printHelloEs6();

// 004
// console이라는 클래스의 log()라는 메서드 사용
console.log('hello', 'bye'); //여러 개 값 출력 시 콤마(,) 사용
// console.warn() = 웹 콘솔에 경고 메세지 출력 시 사용
console.warn(`this line ${'can make error'}`); // Tab 아래 위치한 `(백틱) 사용 // 백틱으로 감싸 템플릿처럼 사용 가능

// 006
// ES6 문법에서 권장하는 방식
// `백틱을 이용한 문자열 formatting
// const 변수 이름 = '문자열';
// console.log(`${변수 이름}`);
const name = 'yaeran';
const sentance = `${name}! nice to meet you!`;
console.log(`${name}! hi!`);
console.log('sentance: ', sentance);

// 008
// 변수 선언 let
// ES5에서의 var와 동일
// BUT var는 같은 이름의 변수를 여러 번 선언하거나 여러 번 정의하는 것이 가능하나
// let은 그렇게 할 시 SyntaxError가 발생함. let은 다른 코드가 재선언한 변수를 이미 사용하고 있는 경우에 발생할 수 있는 문제를 방지해줌.
let fruit = 'apple';
console.log('fruit: ', fruit);
fruit = 'grape'; // let을 다시 쓰는 것이 문제가 될 뿐, 이렇게 선언 이후에 값을 변경하는 것은 문제X.
console.log('fruit: ', fruit);

// 009
// 변수(let)와 상수(const)
// let은 변하는 수 이므로 값을 한 번 넣어준 이후에 다른 값을 넣을 수 있음
// 반면 const는 항상 같은 수이므로 한 번만 값을 넣을 수 있음. 다른 값으로 바꾸려 하면 TypeError 발생.
// 뒤에 프로그램이 복잡할 것을 대비해 최대한 const를 사용하는 것이 좋음.
// const를 기본으로 사용하고 꼭 바뀌는 값이면 다시 let으로 바꾸어 주는 방식 사용.
const pi = 3.14;

// 011
// 숫자인지 판단 .isNaN() = is Not a Number
// 파라미터가 숫자면 false, 숫자가 아닌 다른 타입이면 true 리턴
console.log('isNan: ', isNaN(123));

// cf) 배열 선언 시
const numbers = [5, 6, 7, 8]; // numbers[0]과 같이 접근

// 014
// 문자열 개수 세기 문자열.length
// 배열의 길이 구할 때도 사용 가능
// 역시나 index로 문자열의 일부 문자에 접근 가능
const string = 'hello';
console.log('string length: ', string.length);
console.log('string[0]: ', string[0]);
console.log('string[last length]: ', string[string.length - 1]);

// 015
// 해당 문자열 찾기 indexOf()
// 파라미터로 입력된 문자열이 가장 먼저 등장하는 첫 인덱스 리턴
// 없을 경우 -1 리턴
console.log('ell: ', string.indexOf('ell'));

// 016
// 배열 선언하기
// [] 사용방식
const numbersArray = [1,2,3];
const strings = ['a', 'b', 'c'];
// new Array() 사용방식
const numbers2 = new Array(1,2,3);
// 위 두 방식 둘 다 동일. []를 이용하는게 좋음.
// 두 가지 방식 모두 사용한 방식
let num = []; // 변할 수 있는 빈 배열 선언
num = new Array(1,2,3,4); // let num = new Array(1,2,3,4); 도 가능.
console.log('num: ', num);

// 017
// 배열에 값 넣기 배열.push(값)
const arNumbers = []; // let도 가능
arNumbers.push(1);
arNumbers.push(2, 3);
console.log('arNumbers: ', arNumbers);
// 참고로 배열에 아무것도 없는데 출력할 경우 undefined가 출력됨.

// 019
// JSON = JavaScript Object Notation = JavaScript에서 object(현실의 '어떤 것')를 표현하는 방법
// 한 개의 값뿐만 아니라 json 형식의 오브젝트를 저장함으로써 데이터를 처리하는데 효율적.
const user = {};
user.name = 'yaeran';
user.age = 23;
console.log(user);
const user2 = { name: 'songju', age: 23};
console.log(user2);
console.log('user name: ', user.name); // .을 사용하는 방식을 더 권장
console.log('user age: ', user['age']); // key(필드)를 index 삼아 접근
// 기존에 없던 필드와 값 추가 가능
user.job = 'student';

// 021
// typeof으로 변수의 타입(형) 알아보기
console.log('user: ', typeof user) // object
console.log('[]: %s', typeof []) // object
console.log('{}: %s', typeof {}) // object

// 023
// 후위연산자 존재X
// 증감연산자 존재O

// 024
// 비교연산자
// == 단순히 값만 비교. 5 == '5'
// === 값과 type까지 동일해야 true. 5 !== '5'
const a = 5;
if (a == 5) {
    console.log(a == 5);
    console.log(a == '5');
    console.log(a === 5);
    console.log(a === '5'); // 유일하게 false
}