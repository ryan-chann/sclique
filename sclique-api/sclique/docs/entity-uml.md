---
title: Entity
---
classDiagram

class EntityBase {
-LocalDateTime createdAt
-UUID createdBy
-LocalDateTime modifiedAt
-UUID modifiedBy
-LocalDateTime deletedAt
-UUID deletedBy
-boolean isDeleted
#onCreate()
#onUpdate()
+softDelete()
}

class CommitteeMember {
-UUID id
-byte[] facePhoto
-String name
-String email
-String phoneNumber
-List<OrganisationCommitteeMember> organisations
}

class Organisation {
-UUID id
-String name
-byte[] profilePhoto
-byte[] coverPhoto
-String description
-List<OrganisationCommitteeMember> committeeMembers
}

class OrganisationCommitteeMember {
-UUID id
-CommitteeMember member
-Organisation organisation
}

class Event {
-UUID id
-byte[] eventPhoto
-String title
-String description
-String venue
-int durationInMinutes
-String participationLink
-List<EventFee> eventFee
}

class EventFee {
-Long id
-Event event
-String type
-Double price
}

EntityBase <|-- CommitteeMember
EntityBase <|-- Organisation
EntityBase <|-- OrganisationCommitteeMember
EntityBase <|-- Event
EntityBase <|-- EventFee

