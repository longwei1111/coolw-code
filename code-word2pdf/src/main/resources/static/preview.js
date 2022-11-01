"use strict"
// 启用严格模式
;(function () {
	let modalIndex = 9999 // 初始 modal Z轴
	/**
	 * 粗类型分析
	 * 图片预览：.gif、bmp、jpeg、jpg、png、ico
	 * 文档预览：.doc、docx、xls、xlsx、ppt、pptx
	 * PDF文件：pdf
	 * 文本文件：txt
	 * 音频文件：mp3、ogg、wav
	 * 视频文件：mp4、webm、mkv
	 */
	function formatType(url) {
		const type = url.split(".").pop()
		if (!type) {
			return "txt" // !
		}
		// <img />
		if (/gif|bmp|jpeg|jpg|png|ico|img/i.test(type)) {
			return "img"
		}
		// <iframe></iframe>
		if (/doc|docx|xls|xlsx|ppt|pptx|pdf/i.test(type)) {
			return "pdf"
		}
		// <audio></audio>
		if (/mp3|ogg|wav/i.test(type)) {
			return "mp3"
		}
		// <video></video>
		if (/mp4|webm|mkv/i.test(type)) {
			return "mp4"
		}
		// !
		return "txt"
	}
	/**
	 * 统一样式控制
	 */
	class Style {
		constructor() {
			this.headerStyle = document.getElementById("preview-style")
			if (!this.headerStyle) {
				this.headerStyle = document.createElement("style")
				this.headerStyle.id = "preview-style"
				document.head.appendChild(this.headerStyle)

				this.setHeaderStyle(`
					.preview{
						position: fixed;
						background: rgba(0, 0, 0, .75);
						width: 800px;
						box-shadow: 0 0 5px 0 rgba(0, 0, 0, .35);
						user-select: none;
					}
					.preview .preview-header{
						display: flex;
						justify-content: space-between;
						align-items: center;
						height: 40px;
						cursor: move;
						padding: 0 15px;
						box-sizzing: border-size;
						color: #fff;
					}
					.preview .preview-header span{
						text-overflow: ellipsis;
						white-space: nowrap;
						display: block;
						width: 100%;
					}
					.preview .preview-close{
						border: 0;
						background: transparent;
						width: 30px;
						height: 30px;
						padding: 0;
						cursor: pointer;
					}
					.preview .preview-body{
						overflow: hidden;
						position: relative;
						height: 70vh;
					}
					.preview .preview-body-main{
						width: 100%;
						position: relative;
						cursor: grab;
						transform-origin: center;
					}
					.preview .preview-body-main.grabbing{
						cursor: grabbing;
					}
					.preview .preview-body-main.preview-body-mp3,
					.preview .preview-body-main.preview-body-mp4 {
						height: calc(100% - 46px);
					}
					.preview button:focus{
						border: 0;
						outline: 0;
					}
					.preview-img-warp::before{
						content: '';
						position: absolute;
						width: 100%;
						height: 100%;
						top: 0;
						left: 0;
						z-index: 3;
					}
					.preview-img-warp img{
						display: block;
						width: 100%;
					}
					.preview .preview-operations{
						display: flex;
						justify-content: space-around;
						align-items: center;
						position: absolute;
						bottom: 0;
						left: 0;
						right: 0;
						margin: auto;
						background: rgba(0, 0, 0, .55);
						border-radius: 15px 15px 0 0;
						z-index: 9;
						width: 320px;
						height: 46px;
					}
					.preview .preview-operations span{
						display: flex;
						justify-content: center;
						align-items: center;
						cursor: pointer;
						opacity: .7;
					}
					.preview .preview-operations span:hover{
						border: 0;
					}
					.preview-iframe{
						width: 100%;
						height: 70vh;
						position: absolute;
						top: 0;
						left: 0;
						right: 0;
						margin: auto;
						background: #fff;
					}
					.preview-audio{
						position: absolute;
						top: 0;
						left: 0;
						bottom: 0;
						right: 0;
						margin: auto;
					}
					.preview-audio:focus{
						outline: 0;
					}
					.preview-video{
						position: absolute;
						top: 0;
						left: 0;
						bottom: 0;
						right: 0;
						margin: auto;
						width: 100%;
						height: 100%;
					}
					.preview-video:focus{
						outline: 0;
					}
				`)
			}
		}
		svg = {
			close: `<svg t="1610088739073" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1122" width="16" height="16"><path d="M512 451.67l225.835-225.835a42.667 42.667 0 0 1 60.33 60.33L572.331 512l225.834 225.835a42.667 42.667 0 0 1-60.33 60.33L512 572.331 286.165 798.165a42.667 42.667 0 1 1-60.33-60.33L451.669 512 225.835 286.165a42.667 42.667 0 0 1 60.33-60.33L512 451.669z" p-id="1123" fill="#ffffff"></path></svg>`,
			rotateClockwise: `<svg t="1610097995942" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2039" width="32" height="32"><path d="M1464.3 279.7" p-id="2040" fill="#ffffff"></path><path d="M512 960c-60.5 0-119.1-11.9-174.4-35.2-53.4-22.6-101.3-54.9-142.4-96s-73.4-89-96-142.4C75.9 631.1 64 572.5 64 512s11.9-119.1 35.2-174.4c22.6-53.4 54.9-101.3 96-142.4s89-73.4 142.4-96C392.9 75.9 451.5 64 512 64s119.1 11.9 174.4 35.2c53.4 22.6 101.3 54.9 142.4 96s73.4 89 96 142.4C948.1 392.9 960 451.5 960 512c0 19.1-15.5 34.6-34.6 34.6s-34.6-15.5-34.6-34.6c0-51.2-10-100.8-29.8-147.4-19.1-45.1-46.4-85.6-81.2-120.4C745 209.4 704.5 182 659.4 163c-46.7-19.7-96.3-29.8-147.4-29.8-51.2 0-100.8 10-147.4 29.8-45.1 19.1-85.6 46.4-120.4 81.2S182 319.5 163 364.6c-19.7 46.7-29.8 96.3-29.8 147.4 0 51.2 10 100.8 29.8 147.4 19.1 45.1 46.4 85.6 81.2 120.4C279 814.6 319.5 842 364.6 861c46.7 19.7 96.3 29.8 147.4 29.8 64.6 0 128.4-16.5 184.4-47.8 54.4-30.4 100.9-74.1 134.6-126.6 10.3-16.1 31.7-20.8 47.8-10.4 16.1 10.3 20.8 31.7 10.4 47.8-39.8 62-94.8 113.7-159.1 149.6-66.2 37-141.7 56.6-218.1 56.6z" p-id="2041" fill="#ffffff"></path><path d="M924 552c-19.8 0-36-16.2-36-36V228c0-19.8 16.2-36 36-36s36 16.2 36 36v288c0 19.8-16.2 36-36 36zM275.4 575.5c9.5-2.5 19.1 2.9 22.3 12.2 3.5 10.2 9.9 17.7 19.1 22.6 7.1 3.9 15.1 5.8 24 5.8 16.6 0 30.8-6.9 42.5-20.8 11.7-13.8 20-32.7 24.9-75.1-7.7 12.2-17.3 20.8-28.7 25.8-11.4 5-23.7 7.4-36.8 7.4-26.7 0-47.7-8.3-63.3-24.9-15.5-16.6-23.3-37.9-23.3-64.1 0-25.1 7.7-47.1 23-66.2 15.3-19 37.9-28.6 67.8-28.6 40.3 0 68.1 18.1 83.4 54.4 8.5 19.9 12.7 44.9 12.7 74.9 0 33.8-5.1 63.8-15.3 89.9-16.9 43.5-45.5 65.2-85.8 65.2-27 0-47.6-7.1-61.6-21.2-10-10.1-16.4-22-19.3-35.8-2-9.6 4-19.1 13.5-21.6l0.9 0.1z m103-74.4c9.4-7.5 14.1-20.6 14.1-39.3 0-16.8-4.2-29.3-12.7-37.5S360.6 412 347.5 412c-14 0-25.2 4.7-33.4 14.1-8.2 9.4-12.4 22-12.4 37.7 0 14.9 3.6 26.7 10.9 35.5 7.2 8.8 18.8 13.1 34.6 13.1 11.4 0 21.8-3.8 31.2-11.3zM646.6 414.4c12.4 22.8 18.5 54 18.5 93.7 0 37.6-5.6 68.7-16.8 93.3-16.2 35.3-42.8 52.9-79.6 52.9-33.2 0-57.9-14.4-74.2-43.3-13.5-24.1-20.3-56.4-20.3-97 0-31.4 4.1-58.4 12.2-80.9 15.2-42 42.7-63 82.5-63 35.9 0 61.8 14.8 77.7 44.3z m-40.2 173.3c9.4-13.9 14-39.9 14-78 0-27.4-3.4-50-10.1-67.7-6.8-17.7-19.9-26.6-39.4-26.6-17.9 0-31 8.4-39.3 25.2-8.3 16.8-12.4 41.6-12.4 74.3 0 24.6 2.6 44.4 7.9 59.4 8.1 22.8 22 34.3 41.6 34.3 15.7 0 28.3-7 37.7-20.9zM803.3 387.2c11.2 11.3 16.8 25 16.8 41.2 0 16.7-5.8 30.7-17.5 41.8C791 481.4 777.4 487 762 487c-17.1 0-31.2-5.8-42.1-17.4-10.9-11.6-16.4-25.1-16.4-40.6 0-16.5 5.8-30.4 17.3-41.7 11.5-11.3 25.3-17 41.2-17 16.3 0 30.1 5.7 41.3 16.9zM739.5 451c6.2 6.2 13.7 9.3 22.5 9.3 8.4 0 15.8-3.1 22.1-9.3 6.3-6.2 9.4-13.7 9.4-22.6 0-8.5-3.1-15.9-9.3-22.1-6.2-6.2-13.6-9.3-22.2-9.3s-16.1 3.1-22.4 9.3c-6.3 6.2-9.4 13.7-9.4 22.6-0.1 8.4 3 15.8 9.3 22.1z" p-id="2042" fill="#ffffff"></path></svg>`,
			rotateAntiClockwise: `<svg t="1610098033129" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2390" width="32" height="32"><path d="M924.8 337.6c-22.6-53.4-54.9-101.3-96-142.4s-89-73.4-142.4-96C631.1 75.9 572.5 64 512 64S392.9 75.9 337.6 99.2c-53.4 22.6-101.3 54.9-142.4 96-22.4 22.4-42.2 46.8-59.2 73.1V228c0-19.8-16.2-36-36-36s-36 16.2-36 36v288c0 19.8 16.2 36 36 36s36-16.2 36-36v-50.2c4.2-34.8 13.2-68.7 27-101.2 19.1-45.1 46.4-85.6 81.2-120.4C279 209.4 319.5 182 364.6 163c46.7-19.7 96.3-29.8 147.4-29.8 51.2 0 100.8 10 147.4 29.8 45.1 19.1 85.6 46.4 120.4 81.2C814.6 279 842 319.5 861 364.6c19.7 46.7 29.8 96.3 29.8 147.4 0 51.2-10 100.8-29.8 147.4-19.1 45.1-46.4 85.6-81.2 120.4C745 814.6 704.5 842 659.4 861c-46.7 19.7-96.3 29.8-147.4 29.8-64.6 0-128.4-16.5-184.4-47.8-54.4-30.4-100.9-74.1-134.6-126.6-10.3-16.1-31.7-20.8-47.8-10.4-16.1 10.3-20.8 31.7-10.4 47.8 39.8 62 94.8 113.7 159.1 149.6 66.2 37 141.7 56.6 218.1 56.6 60.5 0 119.1-11.9 174.4-35.2 53.4-22.6 101.3-54.9 142.4-96 41.1-41.1 73.4-89 96-142.4C948.1 631.1 960 572.5 960 512s-11.9-119.1-35.2-174.4z" p-id="2391" fill="#ffffff"></path><path d="M275.4 575.5c9.5-2.5 19.1 2.9 22.3 12.2 3.5 10.2 9.9 17.7 19.1 22.6 7.1 3.9 15.1 5.8 24 5.8 16.6 0 30.8-6.9 42.5-20.8 11.7-13.8 20-32.7 24.9-75.1-7.7 12.2-17.3 20.8-28.7 25.8-11.4 5-23.7 7.4-36.8 7.4-26.7 0-47.7-8.3-63.3-24.9-15.5-16.6-23.3-37.9-23.3-64.1 0-25.1 7.7-47.1 23-66.2 15.3-19 37.9-28.6 67.8-28.6 40.3 0 68.1 18.1 83.4 54.4 8.5 19.9 12.7 44.9 12.7 74.9 0 33.8-5.1 63.8-15.3 89.9-16.9 43.5-45.5 65.2-85.8 65.2-27 0-47.6-7.1-61.6-21.2-10-10.1-16.4-22-19.3-35.8-2-9.6 4-19.1 13.5-21.6l0.9 0.1z m103-74.4c9.4-7.5 14.1-20.6 14.1-39.3 0-16.8-4.2-29.3-12.7-37.5S360.6 412 347.5 412c-14 0-25.2 4.7-33.4 14.1-8.2 9.4-12.4 22-12.4 37.7 0 14.9 3.6 26.7 10.9 35.5 7.2 8.8 18.8 13.1 34.6 13.1 11.4 0 21.8-3.8 31.2-11.3zM646.6 414.4c12.4 22.8 18.5 54 18.5 93.7 0 37.6-5.6 68.7-16.8 93.3-16.2 35.3-42.8 52.9-79.6 52.9-33.2 0-57.9-14.4-74.2-43.3-13.5-24.1-20.3-56.4-20.3-97 0-31.4 4.1-58.4 12.2-80.9 15.2-42 42.7-63 82.5-63 35.9 0 61.8 14.8 77.7 44.3z m-40.2 173.3c9.4-13.9 14-39.9 14-78 0-27.4-3.4-50-10.1-67.7-6.8-17.7-19.9-26.6-39.4-26.6-17.9 0-31 8.4-39.3 25.2-8.3 16.8-12.4 41.6-12.4 74.3 0 24.6 2.6 44.4 7.9 59.4 8.1 22.8 22 34.3 41.6 34.3 15.7 0 28.3-7 37.7-20.9zM803.3 387.2c11.2 11.3 16.8 25 16.8 41.2 0 16.7-5.8 30.7-17.5 41.8C791 481.4 777.4 487 762 487c-17.1 0-31.2-5.8-42.1-17.4-10.9-11.6-16.4-25.1-16.4-40.6 0-16.5 5.8-30.4 17.3-41.7 11.5-11.3 25.3-17 41.2-17 16.3 0 30.1 5.7 41.3 16.9zM739.5 451c6.2 6.2 13.7 9.3 22.5 9.3 8.4 0 15.8-3.1 22.1-9.3 6.3-6.2 9.4-13.7 9.4-22.6 0-8.5-3.1-15.9-9.3-22.1-6.2-6.2-13.6-9.3-22.2-9.3s-16.1 3.1-22.4 9.3c-6.3 6.2-9.4 13.7-9.4 22.6-0.1 8.4 3 15.8 9.3 22.1z" p-id="2392" fill="#ffffff"></path></svg>`,
			enlarge: `<svg t="1610098046936" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3215" width="32" height="32"><path d="M889.5 852.7l-220-219.9c45-53.4 72.1-122.3 72.1-197.5 0-169.4-137.8-307.3-307.3-307.3S127.1 265.8 127.1 435.3s137.8 307.3 307.3 307.3c76.1 0 145.7-27.8 199.4-73.8l219.8 219.8c4.9 5 11.4 7.4 17.9 7.4s13-2.5 17.9-7.4c10-9.9 10-26 0.1-35.9zM434.4 691.8c-141.4 0-256.5-115.1-256.5-256.5 0-141.5 115.1-256.5 256.5-256.5s256.5 115.1 256.5 256.5-115.1 256.5-256.5 256.5z" p-id="3216" fill="#ffffff"></path><path d="M555 418.3h-99.8v-99.8c0-14-11.4-25.4-25.4-25.4s-25.4 11.4-25.4 25.4v99.8h-99.8c-14 0-25.4 11.4-25.4 25.4s11.4 25.4 25.4 25.4h99.8v99.8c0 14 11.4 25.4 25.4 25.4s25.4-11.4 25.4-25.4v-99.8H555c14 0 25.4-11.4 25.4-25.4S569 418.3 555 418.3z" p-id="3217" fill="#ffffff"></path></svg>`,
			narrow: `<svg t="1610098060411" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4013" width="32" height="32"><path d="M713.728 674.432l132.650667 133.546667c5.973333 5.973333 12.074667 18.218667 0 30.336-12.074667 12.16-27.52 2.645333-30.122667 0l-132.693333-133.546667c-49.621333 35.2-113.792 56.149333-174.762667 56.106667A295.253333 295.253333 0 0 1 213.333333 465.749333 295.253333 295.253333 0 0 1 508.8 170.666667c163.2 0 295.509333 132.096 295.509333 295.082666 0.085333 75.221333-38.656 154.026667-90.581333 208.682667zM508.8 217.514667a248.32 248.32 0 0 0-248.448 248.149333 248.32 248.32 0 0 0 248.490667 248.106667 248.234667 248.234667 0 0 0 248.405333-248.106667 248.32 248.32 0 0 0-248.448-248.149333z m134.4 228.266666a22.272 22.272 0 1 1 0 44.586667H374.314667a22.272 22.272 0 1 1 0-44.586667h268.885333z" fill="#ffffff" p-id="4014"></path></svg>`,
			reduction: `<svg t="1610098087818" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4566" width="32" height="32"><path d="M832 905.6H192c-19.2 0-32-12.8-32-32s12.8-32 32-32h640c19.2 0 32 12.8 32 32s-12.8 32-32 32zM800 230.4v448H224v-448h576m32-64H192c-19.2 0-32 12.8-32 32v512c0 19.2 12.8 32 32 32h640c19.2 0 32-12.8 32-32v-512c0-19.2-12.8-32-32-32z" fill="#ffffff" p-id="4567"></path></svg>`,
			prev: `<svg t="1610164347752" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3153" width="32" height="32"><path d="M901.12 191.488c-7.168-26.624-32.768-44.032-60.416-38.912-1.024 0-2.048 0-3.072 1.024h-1.024c-3.072 1.024-8.192 3.072-13.312 6.144L351.232 480.256c-11.264 7.168-19.456 19.456-21.504 32.768-2.048 13.312 0 26.624 8.192 38.912 4.096 6.144 9.216 11.264 16.384 14.336l471.04 307.2c8.192 5.12 18.432 8.192 27.648 8.192 16.384 0 32.768-8.192 43.008-23.552 3.072-5.12 5.12-10.24 6.144-14.336v-2.048c24.576-101.376 37.888-208.896 37.888-318.464 0-111.616-12.288-220.16-38.912-331.776z m-37.888 640c-1.024 2.048-1.024 3.072-1.024 3.072-4.096 5.12-10.24 7.168-15.36 4.096l-472.064-307.2c-1.024 0-1.024-1.024-2.048-1.024l-1.024-1.024c-2.048-3.072-2.048-6.144-2.048-9.216 0-2.048 1.024-4.096 4.096-6.144L845.824 194.56c1.024-1.024 2.048-1.024 3.072-1.024 6.144-1.024 11.264 3.072 12.288 8.192 25.6 107.52 36.864 212.992 36.864 322.56 1.024 106.496-11.264 209.92-34.816 307.2zM174.08 844.8c3.072 11.264-4.096 22.528-15.36 24.576-2.048 0-3.072 1.024-5.12 1.024-9.216 0-17.408-6.144-19.456-15.36C106.496 737.28 92.16 627.712 92.16 527.36c0-126.976 14.336-243.712 41.984-348.16 3.072-11.264 14.336-17.408 24.576-14.336 11.264 3.072 17.408 14.336 14.336 24.576-26.624 101.376-39.936 215.04-39.936 337.92 1.024 96.256 14.336 203.776 40.96 317.44z" p-id="3154" fill="#ffffff"></path></svg>`,
			next: `<svg t="1610164384016" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4056" width="32" height="32"><path d="M677.888 481.28L220.16 161.792c-7.168-5.12-13.312-7.168-18.432-8.192h-1.024c-27.648-5.12-54.272 12.288-60.416 38.912C114.688 304.128 102.4 412.672 102.4 524.288c0 107.52 12.288 214.016 36.864 317.44 1.024 5.12 3.072 11.264 7.168 16.384l1.024 1.024c10.24 13.312 26.624 21.504 41.984 21.504 9.216 0 19.456-3.072 28.672-8.192l459.776-306.176c5.12-3.072 10.24-8.192 13.312-13.312 8.192-11.264 11.264-26.624 9.216-39.936-4.096-14.336-11.264-25.6-22.528-31.744z m-21.504 48.128l-2.048 2.048L194.56 837.632c-5.12 3.072-11.264 1.024-15.36-3.072 0 0-1.024-1.024-1.024-3.072-23.552-99.328-34.816-203.776-34.816-307.2 0-108.544 12.288-214.016 35.84-322.56 1.024-5.12 7.168-9.216 12.288-8.192l4.096 2.048L655.36 515.072c2.048 2.048 3.072 4.096 4.096 5.12 0 3.072 0 6.144-3.072 9.216z m273.408-2.048c0 98.304-13.312 208.896-39.936 326.656-2.048 9.216-10.24 16.384-19.456 16.384h-4.096c-11.264-2.048-18.432-13.312-15.36-24.576C877.568 730.112 890.88 622.592 890.88 527.36c0-121.856-13.312-235.52-38.912-337.92-3.072-11.264 4.096-22.528 14.336-24.576 11.264-3.072 22.528 4.096 24.576 14.336 25.6 105.472 38.912 223.232 38.912 348.16z" p-id="4057" fill="#ffffff"></path></svg>`,
		}
		modal = {
			index: 10000,
		}
		// 为 el 设置 样式
		setStyle(el, css) {
			Object.keys(css).forEach(key => {
				el.style[key] = css[key]
			})
		}
		// 在 header 中追加样式
		setHeaderStyle(css) {
			this.headerStyle.innerHTML += css
		}
		setTransform(el, transform) {
			this.setStyle(el, {
				transform,
			})
		}
	}
	/**
	 * modal 弹窗
	 */
	class Modal extends Style {
		constructor(option) {
			super()
			// 格式化参数
			this.option = this.formatOption(option)
			// 构建内容
			this.createContent()
			// 构建弹窗
			this.create()
		}
		// 弹窗的位置信息
		position = {
			top: 0,
			left: 0,
			oX: 0,
			oY: 0,
		}
		$el // 弹窗根元素
		$children = [] // 弹窗子元素
		body = "" // 弹窗主体部分
		header // 弹窗顶部部分
		operations // 底部操作栏
		_title // 标题
		get title() {
			return this._title
		}
		set title(value) {
			this._title.innerHTML = value || ""
		}
		_visible = false // 控制弹窗是否可见
		get visible() {
			return this._visible
		}
		set visible(visible) {
			if (!visible) {
				this.$el.parentNode.removeChild(this.$el)
			}
			this._visible = visible
		}
		// 当前展示的下标
		get active() {
			return this.option.active
		}
		// 绘制到 index 下标的展示内容
		set active(index) {
			this.option.active = index
			this.drawOperation()
			this.drawBody()
		}
		// 当前展示的内容
		get $displayChild() {
			return this.$children[this.active]
		}

		operationBtns = [] // 操作按钮组
		operationSitting = {
			// 操作按钮文件关联
			img: ["prev", "next", "rotateClockwise", "rotateAntiClockwise", "enlarge", "narrow", "reduction"],
			pdf: ["prev", "next"],
			txt: ["prev", "next"],
			mp3: ["prev", "next"],
			mp4: ["prev", "next"],
		}
		// 操作按钮配置
		operationBtnsObject = [
			{
				id: "prev",
				title: "上一张",
				icon: this.svg.prev,
				handle: this.prev.bind(this),
			},
			{
				id: "next",
				title: "下一张",
				icon: this.svg.next,
				handle: this.next.bind(this),
			},
			{
				id: "rotateClockwise",
				title: "顺时针旋转",
				icon: this.svg.rotateClockwise,
				handle: this.rotateClockwise.bind(this),
			},
			{
				id: "rotateAntiClockwise",
				title: "逆时针旋转",
				icon: this.svg.rotateAntiClockwise,
				handle: this.rotateAntiClockwise.bind(this),
			},
			{
				id: "enlarge",
				title: "放大",
				icon: this.svg.enlarge,
				handle: this.enlarge.bind(this),
			},
			{
				id: "narrow",
				title: "缩小",
				icon: this.svg.narrow,
				handle: this.narrow.bind(this),
			},
			{
				id: "reduction",
				title: "还原",
				icon: this.svg.reduction,
				handle: this.reduction.bind(this),
			},
		]
		// 位置信息记录
		_top = 0
		_left = 0
		_scale = 1
		_rotate = 0
		// 计算当前角度的 cos 值
		get cosVal() {
			return Math.cos((this.rotate * Math.PI) / 180).toFixed(6)
		}
		// 计算当前角度的 sin 值
		get sinVal() {
			return Math.sin((this.rotate * Math.PI) / 180).toFixed(6)
		}
		// 返回当前矩阵位置
		get matrix() {
			return `matrix(${this.cosVal * this.scale}, ${this.sinVal}, ${this.sinVal * -1}, ${this.cosVal * this.scale}, ${this.left}, ${this.top})`
		}
		// 返回|设置 top值
		get top() {
			return this._top
		}
		set top(value) {
			this._top = value
			this.setStyle(this.body, {
				transform: this.matrix,
			})
		}
		// 返回|设置 left值
		get left() {
			return this._left
		}
		set left(value) {
			this._left = value
			this.setStyle(this.body, {
				transform: this.matrix,
			})
		}
		// 返回|设置 缩放值
		get scale() {
			return this._scale
		}
		set scale(value) {
			this._scale = value
			this.setStyle(this.body, {
				transform: this.matrix,
			})
		}
		// 返回|设置 旋转值
		get rotate() {
			return this._rotate
		}
		set rotate(value) {
			this._rotate = value
			this.setStyle(this.body, {
				transform: this.matrix,
			})
		}
		// 当前移动轴的位置
		oX = 0
		oY = 0
		// 切换到前一个
		prev() {
			if (this.active - 1 < 0) {
				return
			}
			this.reduction()
			this.active--
		}
		// 切换到后一个
		next() {
			const length = this.$children.length

			if (this.active + 1 >= length) {
				return
			}
			this.reduction()
			this.active++
		}
		// 顺时针旋转 90 度
		rotateClockwise() {
			this.rotate += 90
		}
		// 逆时针旋转 90 度
		rotateAntiClockwise() {
			this.rotate -= 90
		}
		// 放大 10%
		enlarge() {
			this.up()
		}
		// 缩小 10%
		narrow() {
			this.down()
		}
		// 还原
		reduction() {
			this.top = 0
			this.left = 0
			this.scale = 1
			this.rotate = 0
		}
		// 滚轮操作
		wheel(event) {
			if (event.detail) {
				if (event.detail > 0) {
					this.down()
				} else {
					this.up()
				}
			} else {
				if (event.wheelDelta > 0) {
					this.up()
				} else {
					this.down()
				}
			}
		}
		// 放大方法
		up() {
			if (this.scale + 0.1 >= 3) {
				this.scale = 3
				return
			}
			this.scale += 0.1
		}
		// 缩小方法
		down() {
			if (this.scale - 0.1 <= 0.3) {
				this.scale = 0.3
				return
			}
			this.scale -= 0.1
		}
		// 图片鼠标摁下事件
		mousedown(e) {
			this.body.classList.add("grabbing")
			this.body.onmousemove = this.mousemove.bind(this)
			this.oX = e.pageX
			this.oY = e.pageY
		}
		// 图片鼠标移动事件
		mousemove(e) {
			const left = e.pageX
			const top = e.pageY

			const nX = left - this.oX
			const nY = top - this.oY

			this.oX = e.pageX
			this.oY = e.pageY

			this.top += nY
			this.left += nX
		}
		// 图片鼠标抬起事件
		mouseup() {
			this.body.classList.remove("grabbing")
			this.body.onmousemove = null
		}
		// modal 鼠标摁下事件
		titleMousedown(e) {
			this.header.onmousemove = this.titleMousemove.bind(this)
			this.position.oX = e.pageX
			this.position.oY = e.pageY
		}
		// modal 鼠标移动事件
		titleMousemove(e) {
			const left = e.pageX
			const top = e.pageY

			const nX = left - this.position.oX
			const nY = top - this.position.oY

			this.position.oX = e.pageX
			this.position.oY = e.pageY

			this.position.top += nY
			this.position.left += nX
			if (this.position.top <= 0) {
				this.position.top = 0
			}
			if (this.position.left <= 0) {
				this.position.left = 0
			}

			this.setStyle(this.$el, {
				top: this.position.top + "px",
				left: this.position.left + "px",
			})
		}
		// modal 鼠标抬起事件
		titleMouseup() {
			this.header.onmousemove = null
		}
		// 格式化参数
		formatOption(option) {
			if (!Array.isArray(option.url)) {
				option.url = [option.url]
			}
			return {
				active: 0,
				url: [],
				...option,
			}
		}
		// 设置 modal Z轴
		setModalIndex(index) {
			this.setStyle(this.$el, {
				"z-index": index || ++modalIndex,
			})
		}
		// 构建整体
		create() {
			// 外包围
			const warp = document.createElement("div")
			this.$el = warp
			warp.classList.add("preview")
			warp.onclick = () => {
				this.setModalIndex()
			}
			this.setStyle(warp, {
				top: (this.position.top = window.innerHeight * 0.2) + "px",
				left: (this.position.left = (window.innerWidth - 800) / 2) + "px",
			})
			this.setModalIndex()

			// 头部
			this.header = document.createElement("div")
			this.header.classList.add("preview-header")
			this.header.onmousedown = this.titleMousedown.bind(this)
			this.header.onmouseup = this.titleMouseup.bind(this)
			this._title = document.createElement("span")
			this._title.innerHTML = this.option.title || ""
			const close = document.createElement("button")
			close.innerHTML = this.svg.close
			close.classList.add("preview-close")
			close.onclick = this.closeHandle.bind(this)
			this.header.append(this._title, close)

			// body
			const body = document.createElement("div")
			body.classList.add("preview-body")
			this.$body = body
			this.drawOperation()
			const bodyMain = document.createElement("div")
			bodyMain.classList.add("preview-body-main")
			this.body = bodyMain
			this.drawBody()
			this.$body.append(bodyMain)

			warp.append(this.header, this.$body)
			document.body.append(this.$el)

			window.addEventListener("mouseup", () => {
				this.mouseup()
				this.titleMouseup()
			})
		}
		// 构建内容部分
		createContent() {
			this.option.url.map(url => {
				this.$children.push(
					new Content({
						url,
					}),
				)
			})
		}
		// 关闭事件
		closeHandle() {
			this.visible = false
			this.close && this.close(this.option.args)
		}
		// 绘制操作按钮区
		drawOperation() {
			if (!this.operations) {
				this.operations = document.createElement("div")
				this.operations.classList.add("preview-operations")
			}
			const { type } = this.$displayChild

			const sitting = this.operationSitting[type]
			const operationBtnsObject = this.operationBtnsObject.filter(item => sitting.includes(item.id))

			const operationBtns = operationBtnsObject.map((btn, index) => {
				if (~this.operationBtns.findIndex(item => item.id == btn.id)) {
					return this.operationBtns[index].el
				} else {
					const operationBtn = document.createElement("span")
					operationBtn.title = btn.title
					operationBtn.innerHTML = btn.icon
					operationBtn.onclick = btn.handle
					this.operationBtns.push({
						id: btn.id,
						el: operationBtn,
					})
					return operationBtn
				}
			})
			this.operations.innerHTML = ""
			this.operations.append(...operationBtns)
			this.$body.append(this.operations)
		}
		// 绘制主体区域部分
		drawBody() {
			const { $el, type, title } = this.$displayChild

			this.body.innerHTML = ""
			if (Array.isArray($el)) {
				this.body.append(...$el)
			} else {
				this.body.append($el)
			}
			this.title = title

			const Type = {
				img: () => {
					// 判断是否已经添加过事件
					if (this.body.previewAnimate) {
						return
					}
					this.body.previewAnimate = true
					if (this.body.addEventListener) {
						this.body.addEventListener("DOMMouseScroll", this.wheel.bind(this), false)
					}
					// 添加滚轮事件
					this.body.onmousewheel = this.wheel.bind(this)

					this.body.onmousedown = this.mousedown.bind(this)
					this.body.onmouseup = this.mouseup.bind(this)
				},
				pdf() {},
				txt() {},
				mp3() {
					this.body.classList.add("preview-body-mp3")
					$el.load()
					$el.play()
				},
				mp4() {
					this.body.classList.add("preview-body-mp4")
					$el.load()
					$el.play()
				},

				before() {
					this.body.className = "preview-body-main"
				},
				after() {},
			}

			Type.before.call(this)
			Type[type] && Type[type].call(this)
			Type.after.call(this)
		}
	}
	// 构建主体部分
	// 一次只能构建一个内容
	class Content extends Style {
		constructor(option) {
			super()
			this.option = this.formatOption(option)
			this.title = this.option.url.title
			this.type = this.option.type = formatType(this.option.url.extname)
			this.init()
		}
		type = "" // 记录文件粗解析信息
		formatOption(option) {
			if (!option.url) {
				option.url = ""
			}
			if (typeof option.url == "string") {
				option.url = {
					extname: option.url.split(".").pop(),
					title: option.url.split("/").pop(),
					path: option.url,
				}
			} else {
				option.url = {
					extname: option.url.extname || option.url.path.splice(".").pop() || "img",
					title: option.url.title || option.url.path.split("/").pop() || "",
					path: option.url.path || "",
				}
			}
			return {
				...option,
			}
		}
		init() {
			const {
				type,
				url: { path },
			} = this.option
			const typeHandle = {
				img: () => {
					const img = new DrawImg(path)
					this.$el = img.$el
				},
				pdf: () => {
					const pdf = new DrawPdf(path)
					this.$el = pdf.$el
				},
				txt: () => {},
				mp3: () => {
					const mp3 = new DrawMp3(path)
					this.$el = mp3.$el
				},
				mp4: () => {
					const mp4 = new DrawMp4(path)
					this.$el = mp4.$el
				},
			}
			typeHandle[type] ? typeHandle[type]() : typeHandle["txt"]()
		}
	}

	// 绘制图片类型
	class DrawImg extends Style {
		constructor(url) {
			super()
			this.$el = document.createElement("div")
			this.$el.classList.add("preview-img-warp")
			const img = new Image()
			img.src = url
			this.$el.append(img)

			img.onerror = () => {
				$Preview.error({
					type: "img",
					msg: "图片地址错误",
				})
			}
		}
	}
	// 绘制pdf类型
	class DrawPdf extends Style {
		constructor(url) {
			super()
			this.$el = document.createElement("iframe")
			this.$el.classList.add("preview-iframe")
			this.$el.src = url
			this.$el.scrolling = "auto"
			this.$el.allowtransparency = true
			this.$el.frameborder = "0"
		}
	}
	// 绘制音频类型
	class DrawMp3 extends Style {
		constructor(url) {
			super()
			this.$el = document.createElement("audio")
			this.$el.classList.add("preview-audio")
			this.$el.src = url
			this.$el.controls = true

			this.$el.onerror = () => {
				$Preview.error({
					type: "mp3",
					msg: "音频地址错误",
				})
			}
		}
	}
	// 绘制视频类型
	class DrawMp4 extends Style {
		constructor(url) {
			super()
			this.$el = document.createElement("video")
			this.$el.classList.add("preview-video")
			this.$el.src = url
			this.$el.controls = true

			this.$el.onerror = () => {
				$Preview.error({
					type: "mp4",
					msg: "视频地址错误",
				})
			}
		}
	}

	// 主对象
	class Preview {
		modal = []
		// 展示方法
		/**
		 * url: string | Array<string>
		 * option: {
		 *  active: number = 0
		 * }
		 */
		show(url, option) {
			option = this.formatOption(option)
			const modal = new Modal({
				active: option.active,
				url,
				args: {
					index: this.modal.length,
				},
			})
			modal.close = ({ index }) => {
				this.modal.splice(index, 1)
			}
			this.modal.push(modal)

			return this
		}
		formatOption(option) {
			return {
				url: "",
				active: 0,
				...option,
			}
		}
		closeAll() {
			this.modal.forEach(item => {
				item.closeHandle()
			})
		}
		error(msg) {
			// 暂时处理为alert
			alert(msg.msg)
		}
		formatType = formatType
		formatShowType(url) {
			const type = url.split(".").pop()
			if (!type) {
				return "txt"
			}
			if (/gif|bmp|jpeg|jpg|png|ico|img/i.test(type)) {
				return "img"
			}
			if (/doc|docx/i.test(type)) {
				return "word"
			}
			if (/mp3|ogg|wav/i.test(type)) {
				return "mp3"
			}
			if (/mp4|webm|mkv/i.test(type)) {
				return "mp4"
			}
			return type
		}
	}

	window.$Preview = new Preview()
})()
