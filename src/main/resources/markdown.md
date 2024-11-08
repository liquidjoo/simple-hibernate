```font-jua
```

## Metamodel

JPA의 Metamodel API는 엔티티 클래스의 메타데이터를 제공하여 JPA 쿼리를 작성할 때 타입 안정성을 보장하는데 도움을 주는 API
> 필드 접근 방식이 아닌, 정적 메타모델 클래스를 통해 엔티티 속성을 참조할 수 있게 해줌
>

### 타입 안정성

정적 메타모델을 사용해 타입 안정성을 높여줌

### 유지보수성 향상

문자열 대신 메타모델 클래스의 필드를 사용하므로 엔티티 클래스의 필드명이 변경되어도 코드 내의 쿼리가 영향을 받지 않음

### 가독성 향상

엔티티 속성에 대한 정적 접근 방식을 사용하여 코드 가독성이 향상

---

## JPA Metamodel API의 주요 인터페이스

JPA의 Metamodel 인터페이스는 엔티티 모델 전체의 메타데이터를 표현

`EntityType<T>` 인터페이스
엔티티 클래스의 메타데이터를 나타냄 (속성, 식별자, 관계 등을 포함)

```java-light
public interface EntityType<X> extends ... {
    String getNme(); // Entity name
}
```

`Attribute<X, Y>` 인터페이스
속성 이름, 속성 타입, 속성의 종류(단일 or 컬렉션) 등을 포함

```java-light
public interface Attribute<X, Y> extends ... {
    Class<Y> getJavaType();
    ...
    boolean isAssociation();
    boolean isCollection();
}
```

---

`SingularAttribute<X, T>` 인터페이스
단일 값 속성 e.g) String, int 등의 메타데이터를 제공
Order.name 과 같이 단일 속성에 접근할 때 사용

```java-light
public interface SingularAttribute<X, T> extends ... {
    boolean isId();
    boolean isOptional();
    ...
    Type<T> getType();
}

```

`PluralAttribute<X, C, E>` 인터페이스
컬렉션 값 속성 e.g) List, Set 등의 메타데이터를 제공
컬렉션 타입의 속성 접근에 사용

```java-light
public interface PluralAttribute<X, C, E> extends ... {
    CollectionType getCollectionType();
    Type<E> getElementType();
}
```

---

## Persister

> Persister 의 역할은 무엇이었나?
>

```java-light
public interface Metamodel {
    public EntityPersister entityPersister(Class entityClass);
    public CollectionPersister collectionPersister(String role);
    ...
}
```

> collection persiste 에서 사용되는 role은 `엔티티명.속성명` 의 형식을 나타냄. e.g) Order.order_items
>

---

## Hibernate Metamodel과 JPA Metamodel의 차이점

| 비교 항목           | Hibernate Metamodel                                    | JPA Metamodel                                                     |
|-----------------|--------------------------------------------------------|-------------------------------------------------------------------|
| **주요 구성 요소**    | `EntityPersister`, `CollectionPersister`, `EntityType` | `EntityType`, `Attribute`, `SingularAttribute`, `PluralAttribute` |
| **메타데이터 제공 범위** | 엔티티, 컬렉션, 퍼시스턴스 관련 구체적인 Hibernate 정보                   | 엔티티의 속성, 관계 등 기본 ORM 정보                                           |
| **ORM 세부 제어**   | 가능 (Hibernate 고유 기능에 대한 세부 제어)                         | 제한적                                                               |
| **호환성**         | Hibernate에 종속적                                         | 모든 JPA 구현체에서 호환 가능                                                |

---

## Metadata

Metadata 인터페이스는 Hibernate 에서 데이터베이스와의 매핑 정보를 구성하고, 이정보를 바탕으로 세션팩토리(엔티티매니저 팩토리)를 생성하는데 필요한 메타데이터를 제공

- Hibernate 의 ORM 설정과 매핑정보를 초기화 하는 역할
- Hibernate Spec (JPA 는 없음)

### 주요 메서드

- **getEntityBindings()**: 모든 엔티티에 대한 매핑 정보를 반환
- **getIdentifierGenerator()**: 특정 엔티티의 식별자 생성 전략을 생성
- **getDatabase()**: 데이터베이스 관련 메타데이터를 반환. 스키마 생성이나 관리에 유용

### 초기화 순서

Metadata 초기화 -> SessionFactory(EntityManagerFactory) 생성 -> Medamodel 초기화

---
