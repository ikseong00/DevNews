# 이슈 워크플로우

## 이슈 제목 형식

```
[{Type}]: {설명}
```

**Type 종류**: `Feat`, `Fix`, `Refactor`, `Chore`, `Docs`, `CI`

### 예시

```
[Feat]: 홈 화면 아티클 리스트 구현
[Fix]: 카테고리 필터 선택 시 크래시 수정
[Chore]: Desktop/Web 타겟 제거
```

## 이슈 템플릿 선택 기준

| 템플릿 | 사용 상황 |
|--------|-----------|
| `feature-task` | 새로운 기능 구현 |
| `fix-task` | 버그 수정 |
| `chore-task` | 설정, 의존성, 빌드 관련 작업 |

## 라벨

### Type 라벨

`feat`, `fix`, `refactor`, `chore`, `docs`, `ci`

### Phase 라벨

| 라벨 | 설명 |
|------|------|
| `phase-0` | Foundation Setup |
| `phase-1` | Data Layer |
| `phase-2` | Navigation + Home Screen |
| `phase-3` | Detail Screen |
| `phase-4` | Favorites & History (Room KMP) |
| `phase-5` | Settings |
| `phase-6` | Polish |

이슈 생성 시 **type 라벨 + phase 라벨** 모두 할당.

## 이슈 본문 구조

```markdown
## Description

{이슈에 대한 간결한 설명}

## Tasks

- [ ] 태스크 1
- [ ] 태스크 2
- [ ] 태스크 3

## Dependencies

Depends on #{이슈번호}
```

- `Tasks`는 구현할 세부 항목을 체크리스트로 작성
- 의존성이 있는 경우 `Depends on #X` 형식으로 참조
- 의존성이 없으면 Dependencies 섹션 생략
