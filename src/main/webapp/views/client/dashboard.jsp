<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord - Espace Client</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #3f51b5;
            color: white;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header h1 {
            margin: 0;
        }

        .btn-deconnexion {
            background-color: #e53935;
            color: white;
            padding: 10px 18px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
        }

        .btn-deconnexion:hover {
            background-color: #c62828;
        }

        .container {
            max-width: 900px;
            margin: 30px auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 30px;
        }

        h2 {
            color: #3f51b5;
            margin-top: 0;
        }

        .info-section {
            margin-bottom: 30px;
        }

        .info-section p {
            margin: 6px 0;
            font-size: 16px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #3f51b5;
            color: white;
        }

        .actions {
            text-align: center;
            margin-top: 30px;
        }

        .btn {
            display: inline-block;
            margin: 10px 10px;
            padding: 12px 22px;
            background-color: #3f51b5;
            color: white;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
        }

        .btn:hover {
            background-color: #303f9f;
        }

        .btn-green {
            background-color: #4CAF50;
        }

        .btn-green:hover {
            background-color: #388E3C;
        }
    </style>
</head>
<body>

<div class="header">
    <h1>Bienvenue, <c:out value="${client.nom}" /></h1>
    <a href="${pageContext.request.contextPath}/views/login.jsp" class="btn-deconnexion">🚪 Déconnexion</a>
</div>

<div class="container">

    <div class="info-section">
        <h2>Mes Informations</h2>
        <p><strong>Email :</strong> <c:out value="${client.email}" /></p>
        <p><strong>Rôle :</strong> <c:out value="${client.role}" /></p>
    </div>

    <div class="info-section">
        <h2>Mon Compte Bancaire</h2>
        <p><strong>Numéro :</strong> <c:out value="${compte.numero}" /></p>
        <p><strong>Devise actuelle :</strong> <c:out value="${compte.devise}" /></p>

        <table>
            <thead>
            <tr>
                <th>Devise</th>
                <th>Montant Converti</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${taux}">
                <tr>
                    <td>${entry.key}</td>
                    <td>
                        <fmt:formatNumber value="${compte.solde * entry.value}" type="currency" currencySymbol="${entry.key}" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/credit/add" class="btn btn-green">➕ Faire une demande de crédit</a>
        <a href="${pageContext.request.contextPath}/credit/list" class="btn">📋 Voir mes demandes de crédit</a>
    </div>

</div>

</body>
</html>
