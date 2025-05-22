<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="user" value="${sessionScope.user}" />
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Utilisateurs - Admin</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 30px;
        }

        h2 {
            color: #3f51b5;
            text-align: center;
            margin-bottom: 30px;
        }

        .btn-ajouter, .btn-retour {
            display: inline-block;
            margin: 10px 10px 20px 0;
            padding: 10px 20px;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            color: white;
        }

        .btn-ajouter {
            background-color: #2196F3;
        }

        .btn-ajouter:hover {
            background-color: #1976D2;
        }

        .btn-retour {
            background-color: #607d8b;
        }

        .btn-retour:hover {
            background-color: #455a64;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        th, td {
            padding: 12px 16px;
            border-bottom: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #3f51b5;
            color: white;
        }

        .btn-action {
            padding: 6px 10px;
            font-size: 13px;
            margin: 0 3px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .btn-edit {
            background-color: #FFC107;
            color: black;
        }

        .btn-edit:hover {
            background-color: #e0a800;
        }

        .btn-delete {
            background-color: #e53935;
            color: white;
        }

        .btn-delete:hover {
            background-color: #c62828;
        }

        .empty-message {
            text-align: center;
            color: #666;
            margin-top: 30px;
        }
    </style>
</head>
<body>

<h2>Liste des Utilisateurs</h2>

<a href="${pageContext.request.contextPath}/admin/user/add" class="btn-ajouter">➕ Ajouter un utilisateur</a>
<a href="${pageContext.request.contextPath}/admin/dashboard" class="btn-retour">← Retour à l’accueil</a>

<c:if test="${empty utilisateurs}">
    <p class="empty-message">Aucun utilisateur enregistré.</p>
</c:if>
<c:if test="${not empty message}">
    <div style="background-color: #d4edda; color: #155724; padding: 10px; margin-bottom: 20px; border-radius: 5px; border: 1px solid #c3e6cb;">
            ${message}
    </div>
</c:if>


<c:if test="${not empty utilisateurs}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Rôle</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${utilisateurs}">
            <tr>
                <td>${u.id}</td>
                <td>${u.nom}</td>
                <td>${u.email}</td>
                <td>${u.role}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/user/edit?id=${u.id}" class="btn-action btn-edit">Modifier</a>
                    <a href="${pageContext.request.contextPath}/admin/user/delete?id=${u.id}" class="btn-action btn-delete"
                       onclick="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?');">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
