package com.saks.balance.states;

public class GlobalStates {
    // 유저 관련
    public enum Authority{
        Admin,  // 관리자
        User,   // 일반
        Host    // 게임 호스트
    }

    public enum AccoutState{
        Active, // 계정 활성화 됨
        Inactive,   // 계정 비활성화 됨(휴먼계정 등, 자체적으로 재활성화 가능)
        Pending_Verification,   // 가입 요청했으나 이메일 등 승인 대기중
        Suspended,  // 정책 위반 등으로 계정 정지상태
        Banned, // 정책 위반으로 계정 차단 됨
        Deleted,    // 삭제된 계정
        Locked, // 보안상의 이유로 차단된 계정(로그인 여러번 실패, 혹은 해킹 시도 등)
        Expired // 계정 비활성화 됨(매우 오랜시간 활동 없음, 관리자 요청으로 재활성화 가능)
    }

    public enum SnsType{
        Normal, // 일반 회원가입 계정(SNS 연동 X)
        Naver,  // 네이버 연동
        Kakao,  // 카카오 연동
        Google  // 구글 연동
    }

    // 추천인 관련
    public enum UserRefferalState{
        Active,     // 추천인 활성화 됨(이 계정을 추천인으로 가입 가능)
        Inactive,   // 추천인 권한 비활성화 됨(더 이상 이 계정으로 추천인 가입 불가)
        Suspended,  // 추천인 취소 됨(정책위반, 조건 변경 등)
        Pending_Approval,   // 추천인 권한 승인 대기중
        Limit_Reached   // 추천인 최대 가입 수 만큼 가입됨, 더 이상 이 계정으로 추천인 가입 불가
    }

    public enum GameState{
        Pending,    // 게임 활성화 대기 중
        Active, // 게임 활성화 됨(투표가능)
        Inactive,   // 게임이 비활성화 됨
        Completed,  // 게임이 정상적으로 종료 됨
        Suspended,  // 게임이 차단됨(정책상의 이유)
        Expired,    // 예약된 게임 수명이 지남, 종료해야 함
        Deleted     // 삭제된 게임(정상적이지 않고 모종의 이유로 삭제됐을 경우, 호스트 수동 삭제 등)
    }

    public enum GameBetType{
        FixedBet,   // 고정 판돈, 판돈 수치만큼 게임에 참가한 모든 유저가 동일한 판돈을 검
        PercentageBet   // 비율 판돈, 비율만큼 게임에 참가한 각각의 유저가 본인이 보유한 게임머니의 일정 비율만큼을 판돈으로 검

    }

    // 결제 관련

}
