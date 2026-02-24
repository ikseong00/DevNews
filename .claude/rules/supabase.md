# Supabase 연동 가이드

## 프로젝트 정보

- **Project ID**: `iqbygidnorgcxxcdmbqm`
- **Region**: 프로젝트 설정 참조

## tech_blog_articles 테이블 스키마

| 컬럼 | 타입 | 설명 | 비고 |
|------|------|------|------|
| `id` | `bigint` | PK, auto-increment | GENERATED ALWAYS AS IDENTITY |
| `title` | `text` | 아티클 제목 | NOT NULL |
| `link` | `text` | 원문 URL | NOT NULL, UNIQUE |
| `summary` | `text` | 요약/설명 | nullable |
| `category` | `article_category` | 카테고리 enum | nullable |
| `blog_source` | `text` | 출처 블로그명 | NOT NULL |
| `published_at` | `timestamptz` | 게시일 | nullable |
| `created_at` | `timestamptz` | DB 생성일 | default: now(), nullable |

## article_category enum (13종)

```
AI, Android, Automation, Cross-platform, Data, DevOps, Hiring, Infra, iOS, PM, QA, Server, Web
```

## blog_source 목록 (16개)

| 국내 | 해외 |
|------|------|
| 네이버 D2, 당근, 라인, 마켓컬리, 무신사, 쏘카, 여기어때, 요기요, 우아한형제들, 카카오, 쿠팡테크, 토스 | Airbnb Tech Blog, Google Developers, Meta Engineering, Netflix Tech Blog |

## RLS 정책

| 정책명 | 작업 | 역할 | 조건 |
|--------|------|------|------|
| Anyone can read articles | SELECT | public | `true` (모든 사용자 읽기 가능) |
| Service role can insert articles | INSERT | public | service_role만 삽입 가능 |

앱에서는 **anon key로 SELECT만** 수행. INSERT/UPDATE/DELETE는 서버사이드(Edge Function)에서만.

## ArticleDto 매핑

```kotlin
@Serializable
data class ArticleDto(
    val id: Long,
    val title: String,
    val link: String,
    val summary: String? = null,
    val category: String? = null,
    @SerialName("blog_source")
    val blogSource: String,
    @SerialName("published_at")
    val publishedAt: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
)
```

## Postgrest 쿼리 패턴

### 페이지네이션

```kotlin
supabase.from("tech_blog_articles")
    .select()
    .order("published_at", Order.DESCENDING)
    .range(from = offset.toLong(), to = (offset + limit - 1).toLong())
```

### 카테고리 필터

```kotlin
supabase.from("tech_blog_articles")
    .select()
    .eq("category", category.name)
    .order("published_at", Order.DESCENDING)
    .range(from = offset.toLong(), to = (offset + limit - 1).toLong())
```

### 키워드 검색

```kotlin
supabase.from("tech_blog_articles")
    .select()
    .or("title.ilike.%$keyword%,summary.ilike.%$keyword%")
    .order("published_at", Order.DESCENDING)
```

## 주의사항

- `published_at`이 null인 경우 `created_at`을 fallback으로 사용
- `category`가 null인 경우가 있으므로 UI에서 "전체" 카테고리로 분류
- `id`는 bigint (Long) 타입. UUID가 아님
- `link`가 UNIQUE 제약조건이므로 중복 아티클 없음
