var j = 0;
function update(i) {
	if (i == 0) {
		let htmlCode = document.getElementById("htmlCode").value;
		let cssCode = document.getElementById("cssCode").value;
		let javascriptCode = document.getElementById("javascriptCode").value;

		let text = htmlCode + "<style>" + cssCode + "</style>" + "<scri" + "pt>" + javascriptCode + "</scri" + "pt>";
		let iframe = document.getElementById('viewer').contentWindow.document;
		iframe.open();
		iframe.write(text);
		iframe.close();
	}
	else if (i == 1) {
		let htmlCode = document.getElementById("htmlCode").value;
		let html = htmlCode.slice(0, htmlCode.length);
		document.getElementById("htmlCode").value = html;
		j = 1;
	}
}

const closeChars = new Map([
	['{', '}'],
	['[', ']'],
	['(', ')'],
	['<', '>'],
	['"', '"'],
	["'", "'"]
]);

htmlCode = document.getElementById('htmlCode');
htmlCode.addEventListener('input', function (e) {
	if (j != 1) {
		const pos = e.target.selectionStart;
		const val = [...e.target.value];
		const char = val.slice(pos - 1, pos)[0];// suppose (
		const closeChar = closeChars.get(char);
		if (closeChar) {
			val.splice(pos, 0, closeChar);
			e.target.value = val.join('');
			e.target.selectionEnd = pos;
		}
	}
	j = 0;
});

cssCode = document.getElementById('cssCode');
cssCode.addEventListener('input', function (e) {
	if (j != 1) {
		const pos = e.target.selectionStart;
		const val = [...e.target.value];
		const char = val.slice(pos - 1, pos)[0];
		const closeChar = closeChars.get(char);
		if (closeChar) {
			val.splice(pos, 0, closeChar);
			e.target.value = val.join('');
			e.target.selectionEnd = pos;
		}
	}
	j = 0;
});

javascriptCode = document.getElementById('javascriptCode');
javascriptCode.addEventListener('input', function (e) {
	if (j != 1) {
		const pos = e.target.selectionStart;
		const val = [...e.target.value];

		const char = val.slice(pos - 1, pos)[0];
		const closeChar = closeChars.get(char);

		if (closeChar) {
			val.splice(pos, 0, closeChar);
			e.target.value = val.join('');
			e.target.selectionEnd = pos;
		}
	}
	j = 0;
});

Split([".container", ".iframe-container"]);


function goProfile() {
	window.location.href = "https://github.com/Ahmed-DotNetDev";
}