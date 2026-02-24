# PR 워크플로우

## 브랜치 전략

```
main (프로덕션) ← develop (개발 통합) ← feature branches
```

### 브랜치 네이밍

```
{type}/#{issue}-{kebab-description}
```

**예시:**
```
feat/#9-home-screen
fix/#15-category-filter-crash
chore/#2-add-dependencies
```

## PR 제목 형식

```
[{Type}] #{issue}: {설명}
```

**예시:**
```
[Feat] #9: 홈 화면 구현
[Fix] #15: 카테고리 필터 크래시 수정
[Chore] #2: 핵심 의존성 추가
```

## PR 본문 구조

```markdown
Close #{issue}

## 작업 내용

- 구현/변경한 내용 1
- 구현/변경한 내용 2

## 테스트

- [ ] 빌드 확인 (Android)
- [ ] 빌드 확인 (iOS)
- [ ] 기능 동작 확인

## 스크린샷 (UI 변경 시)

| Before | After |
|--------|-------|
|        |       |
```

## 규칙

- **base 브랜치**: 항상 `develop`
- **머지 전략**: Squash merge
- **PR 크기**: 200~500줄 권장. 500줄 초과 시 분할 고려
- 하나의 PR은 하나의 이슈에 대응
- `Close #{issue}`로 이슈 자동 닫기 연결
