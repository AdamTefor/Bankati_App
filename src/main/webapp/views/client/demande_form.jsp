<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Nouvelle Demande de Crédit - Bankati</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #3f51b5;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
        }

        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background-color: #3f51b5;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #303f9f;
        }

        .erreur {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            border: 1px solid #f5c6cb;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        .lien-retour {
            display: block;
            margin-top: 15px;
            text-align: center;
            color: #3f51b5;
            text-decoration: none;
        }

        .lien-retour:hover {
            text-decoration: underline;
        }

        .btn-retour {
            display: inline-block;
            margin-top: 10px;
            padding: 10px 18px;
            background-color: #607d8b;
            color: white;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
        }

        .btn-retour:hover {
            background-color: #455a64;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Nouvelle Demande de Crédit</h2>

    <c:if test="${not empty erreur}">
        <div class="erreur">${erreur}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/credit/add" method="post">
        <div class="form-group">
            <label for="montant">Montant souhaité (en DH) :</label>
            <input type="number" name="montant" id="montant" required min="100" step="100" placeholder="Ex: 5000">
        </div>

        <button type="submit" class="btn">Envoyer la demande</button>
    </form>

    <div style="margin-top: 30px; display: flex; gap: 15px;">
        <a href="${pageContext.request.contextPath}/credit/list" class="btn-retour bleu">← Retour à mes demandes</a>
        <a href="${pageContext.request.contextPath}/client/dashboard" class="btn-retour gris">← Retour à l’accueil</a>
    </div>


</div>

</body>
</html>

