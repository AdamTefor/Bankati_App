<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="user" value="${sessionScope.user}" />
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Demandes de Crédit - Admin</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 30px;
        }

        h2 {
            text-align: center;
            color: #3f51b5;
            margin-bottom: 30px;
        }

        .btn-retour {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            background-color: #607d8b;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 6px;
        }

        .btn-retour:hover {
            background-color: #455a64;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
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

        .etat {
            font-weight: bold;
            text-transform: uppercase;
        }

        .etat.EN_ATTENTE {
            color: orange;
        }

        .etat.APPROUVÉE {
            color: green;
        }

        .etat.REFUSÉE {
            color: red;
        }

        .btn {
            padding: 6px 10px;
            font-size: 13px;
            margin: 0 4px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }

        .btn-approve {
            background-color: #4CAF50;
            color: white;
        }

        .btn-approve:hover {
            background-color: #388E3C;
        }

        .btn-refuse {
            background-color: #e53935;
            color: white;
        }

        .btn-refuse:hover {
            background-color: #c62828;
        }

        .empty-message {
            text-align: center;
            font-size: 16px;
            color: #666;
            margin-top: 40px;
        }
    </style>
</head>
<body>

<h2>Demandes de Crédit des Clients</h2>

<a href="${pageContext.request.contextPath}/admin/dashboard" class="btn-retour">← Retour à l’accueil</a>

<c:if test="${empty demandes}">
    <p class="empty-message">Aucune demande enregistrée pour le moment.</p>
</c:if>

<c:if test="${not empty demandes}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>ID Client</th>
            <th>Montant</th>
            <th>Statut</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${demandes}">
            <tr>
                <td>${d.id}</td>
                <td>${d.idClient}</td>
                <td><fmt:formatNumber value="${d.montant}" type="number" minFractionDigits="2"/></td>
                <td class="etat ${d.statut}">${d.statut}</td>
                <td>
                    <c:if test="${d.statut == 'EN_ATTENTE'}">
                        <a href="${pageContext.request.contextPath}/credit/approve?id=${d.id}" class="btn btn-approve">✅ Approuver</a>
                        <a href="${pageContext.request.contextPath}/credit/refuse?id=${d.id}" class="btn btn-refuse">❌ Refuser</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
