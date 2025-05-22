<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="mode" value="${mode != null ? mode : 'ajout'}" />

<c:choose>
    <c:when test="${not empty utilisateur}">
        <c:set var="user" value="${utilisateur}" />
    </c:when>
    <c:otherwise>
        <c:set var="user" value="${null}" />
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>${mode == 'edition' ? 'Modifier' : 'Ajouter'} un utilisateur</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #3f51b5;
            margin-bottom: 25px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 6px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background-color: #3f51b5;
            color: white;
            border: none;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #303f9f;
        }

        .btn-retour {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #607d8b;
            text-decoration: none;
        }

        .btn-retour:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>${mode == 'edition' ? 'Modifier l’utilisateur' : 'Ajouter un utilisateur'}</h2>

    <form action="${pageContext.request.contextPath}/admin/user/save" method="post">

    <c:if test="${mode == 'edition'}">
            <input type="hidden" name="id" value="${user.id}" />
        </c:if>

        <label for="nom">Nom complet :</label>
        <input type="text" id="nom" name="nom" required value="${not empty user ? user.nom : ''}" />

        <label for="email">Adresse email :</label>
        <input type="email" id="email" name="email" required value="${not empty user ? user.email : ''}" />

        <label for="motDePasse">Mot de passe :</label>
        <input type="text" id="motDePasse" name="motDePasse" required value="${not empty user ? user.motDePasse : ''}" />

        <label for="role">Rôle :</label>
        <select name="role" id="role" required>
            <option value="">-- Sélectionner un rôle --</option>
            <option value="CLIENT" <c:if test="${not empty user and user.role == 'CLIENT'}">selected</c:if>>Client</option>
            <option value="ADMIN" <c:if test="${not empty user and user.role == 'ADMIN'}">selected</c:if>>Administrateur</option>
        </select>

        <button type="submit" class="btn">
            ${mode == 'edition' ? 'Enregistrer les modifications' : 'Ajouter l’utilisateur'}
        </button>
    </form>

    <a href="${pageContext.request.contextPath}/admin/users" class="btn-retour">← Retour à la liste des utilisateurs</a>
</div>

</body>
</html>
