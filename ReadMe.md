화상채팅 웹 어플리케이션을 실행시키기 위해
1. Spring web Server 폴더의 "서버 실행시 꼭 읽어주세요.txt" 파일을 읽고 절차에 따라 실행시켜주세요
2. express server. 폴더의 "서버 실행시 꼭 읽어주세요.txt" 파일을 읽고 절차에 따라 실행시켜주세요



Face Talk 소개


 ‘Face talk’ 웹 어플리케이션은 웹을 통한 화상채팅 어플리케이션 입니다.
	
학교 혹은 외부에서 자주 사용하는 웹 화상채팅(zoom, google meet 등)을 사용하면서 불편한 점과 추가되었으면 좋을 기능을 생각해보았지만 zoom 혹은 meet 등의 플랫폼에서 저희 의견을 반영하여 현재 문제없이 진행되고 있는 프로젝트에 변경이 이루어지기에는 어려움이 있다 생각해 저희 팀만의 독자적인 화상채팅을 구현하여 저희가 바라는 기능과 다른 플랫폼에서의 불편했던 점을 개선해보자는 동기로 “Face talk”프로젝트를 진행하게 되었습니다.


실행화면 


![image](https://user-images.githubusercontent.com/81874493/139874695-35573259-9400-41a4-ae5d-5f62475d85f9.png)


![화상회의 사진](https://user-images.githubusercontent.com/81874493/139874765-5db33fb7-b5eb-4d99-931c-d7e425c10507.JPG)




Zoom 클론코딩 이후 추가한 기능
1. 화상회의에서 정리한 내용을 저장하고 수정 삭제가 가능하다.
2. 화상회의 방에 입장시 메세지를 통하여 화상채팅 방의 코드를 공유 할 수 있다.




프로젝트 과정 중 문제점 및 시행착오

프로젝트 과정 중 문제점 및 시행착오에는 첫 번째로 처음 접해보는 WebRTC기술에 대한 이해가 쉽지 않았었습니다.
WebRTC 기술이란 무엇이고 어떠한 방식으로 두 peer 간의 영상과 음성의 스트림이 전송되며 서버와 클라이언트가 연결되는지의 기본적인 이해와 WebRTC 기술을 편리하게 사용하게 해주는 peer js 라이브러리의 사용법에 대한 숙지에 많은 시간을 보내게 되었습니다.
두 번째로는 SSL의 문제가 있었습니다.
화상 채팅에는 영상과 음성 데이터를 사용하기 때문에 HTTPS 프로토콜 기반으로 동작합니다 외부의 다른 클라이언트가 접속을 위해 SSL 적용이 필요했으며 Express 웹서버와 Spring 웹서버 간 데이터 통신이 있어 HTTP와 HTTPS 간 통신은 지원하지 않아 두서버 모두 SSL 적용에 많은 시행착오가 있었습니다.
마지막으로는 서버 간 통신에 문제가 있었습니다.
화상회의 이후 넘어가는 문서 데이터의 통신에 CORS(Cross-Origin Resource Sharing) 정책 위반으로 인한 에러 해결에 많은 시간이 소비되었습니다.




추후 개선 방향 

첫 번째, 뷰에 대한 분리를 할 예정입니다.
현재 서버는 Spring 웹서버와 ,Express 웹서버 모두 SSR(Server Side Rendering) 방식을 사용하고 있습니다.
이후 백 엔드와 프론트 엔드의 구분을 명확히 두어 CSR(Client Side Rendering) 방식을 통해 각 서버 역할에만 집중할 수 있도록 개선할 예정입니다. 
두 번째, AWS를 이용하여 프로젝트 배포해 외부에서 Domain을 통한 외부 접근이 가능하도록 개선할 예정입니다.
세 번째, 추후 사용해보며 사용자들의 불편했던 점 과 필요한 기능들에 대해 적극 반영하여 개선해볼 예정입니다.




참고 자료


Youtube 영상,  https://www.youtube.com/watch?v=DvlyzDZDEq4&t=250s,

Github , https://github.com/WebDevSimplified/Zoom-Clone-With-WebRTC

티스토리, https://withseungryu.tistory.com/129 

peerjs, https://peerjs.com 

softWare Architect, https://www.lesstif.com/software-architect/openssl-command-tip-7635159.html 

1분코딩, https://studiomeal.com/archives/197 

HTML5 UP https://html5up.net/ 
